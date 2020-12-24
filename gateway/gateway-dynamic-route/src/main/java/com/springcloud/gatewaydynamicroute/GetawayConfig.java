package com.springcloud.gatewaydynamicroute;

import org.springframework.beans.factory.annotation.Configurable;
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
        //跳转到百度新闻国内栏目
        routes.route("news", (r) ->
                r.path("/guonei/**").
                        uri("http://news.baidu.com/guonei"))

//                .route("cookie_route", r -> r.cookie("TotalPage", "14")
//                        .uri("http://www.baidu.com"))
                .route("cookie_route", r -> r.header("demo", "test")
                        .uri("https://spring.io/"))
                .route("host_route", r -> r.host("**.wanglin.com")
                        .uri("https://spring.io/"))
                .route("search", (r) ->
                        r.path("/search").filters(f -> f.stripPrefix(1)).
                                uri("http://www.baidu.com")).build();
        return routes.build();
    }
}
