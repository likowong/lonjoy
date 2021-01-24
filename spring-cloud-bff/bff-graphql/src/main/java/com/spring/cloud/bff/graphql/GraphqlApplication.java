package com.spring.cloud.bff.graphql;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author luke
 * @date 2021/1/22  14:02
 * @desc
 **/
@DubboComponentScan
@EnableDiscoveryClient
@SpringBootApplication
public class GraphqlApplication {
    public static void main(String[] args) {
       SpringApplication.run(GraphqlApplication.class, args);
    }
}
