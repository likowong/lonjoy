package com.springcloud.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author luke
 * @date 2021/1/22  14:02
 * @desc
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GraphqlMain {
    public static void main(String[] args) {
        SpringApplication.run(GraphqlMain.class, args);
    }
}
