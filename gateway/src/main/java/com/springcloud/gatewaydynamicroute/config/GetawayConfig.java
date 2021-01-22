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
        routes.route("bff-graphql", (r) -> r.path("/bff-graphql/**")
                        .uri("lb://bff-graphql"))
                .build();
        return routes.build();
    }
}
