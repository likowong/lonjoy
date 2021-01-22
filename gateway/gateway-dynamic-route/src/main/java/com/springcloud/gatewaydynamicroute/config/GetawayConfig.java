package com.springcloud.gatewaydynamicroute.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luke
 * @date 2020/12/9 22:50
 * @desc java api配置方式
 **/
@Configuration
public class GetawayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        // 配置路由转发
        routes.route("nacos-consumer", (r) -> r.path("/nacos-consumer/**")
                        .uri("lb://nacos-consumer"))
                .route("nacos-client", (r) -> r.path("/nacos-client/**")
                        .uri("lb://nacos-client"))
                .build();
        return routes.build();
    }
}
