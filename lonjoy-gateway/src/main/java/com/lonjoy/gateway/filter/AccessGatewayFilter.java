package com.lonjoy.gateway.filter;

import com.lonjoy.auth.api.params.PermissionParams;
import com.lonjoy.auth.api.vo.UserInfo;
import com.lonjoy.auth.client.service.AuthRemoteInterface;
import com.lonjoy.common.ApplicationException;
import com.lonjoy.common.ApplicationResponseCode;
import com.lonjoy.common.ResResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

/**
 * 请求url权限校验
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private final static String X_CLIENT_OPENID = "x-client-openId";
    private final static String X_CLIENT_TYPE = "x-client-type";
    private final static String X_CLIENT_NAME = "x-client-name";
    private final static String X_CLIENT_TOKEN = "x-client-token";

    @Autowired
    private AuthRemoteInterface authRemoteService;

    @Value("${gateway.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth,/v2/api-docs";

    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String clientType = request.getHeaders().getFirst(X_CLIENT_TYPE);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{},clientType:{}", url, method, request.getHeaders(), clientType);

        //不需要网关签权的url
        if (Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)))) {
            return chain.filter(exchange);
        }
        if (StringUtils.isEmpty(authentication)) {
            throw new ApplicationException(ApplicationResponseCode.TOKEN_IS_EMPTY_CODE);
        }
        if (StringUtils.isEmpty(clientType)) {
            throw new ApplicationException(ApplicationResponseCode.CLIENT_TYPE_IS_EMPTY_CODE);
        }

        //调用签权务看用户是否有权限，若有权限进入下一个filter
        PermissionParams permissionParams = new PermissionParams();
        permissionParams.setToken(authentication);
        permissionParams.setUrl(url);
        permissionParams.setClientType(clientType);
        // 鉴权
        ResResult<Boolean> booleanResResult = authRemoteService.hasPermission(permissionParams);
        if (!booleanResResult.isSuccess()) {
            throw new ApplicationException(new ApplicationResponseCode(booleanResResult.getCode(), booleanResResult.getStatus(), booleanResResult.getMessage()));
        }
        // 有权限则路由到下游服务
        if (booleanResResult.getData()) {
            ResResult<UserInfo> userInfo = authRemoteService.getUserInfo(authentication);
            if (!userInfo.isSuccess()) {
                throw new ApplicationException(new ApplicationResponseCode(userInfo.getCode(), userInfo.getStatus(), userInfo.getMessage()));
            }
            ServerHttpRequest.Builder builder = request.mutate();
            //将jwt token中的用户信息传给服务
            builder.header(X_CLIENT_OPENID, userInfo.getData().getOpenId());
            builder.header(X_CLIENT_NAME, userInfo.getData().getName());
            builder.header(X_CLIENT_TOKEN, authentication);
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        // 无权限
        return unauthorized(exchange);
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
