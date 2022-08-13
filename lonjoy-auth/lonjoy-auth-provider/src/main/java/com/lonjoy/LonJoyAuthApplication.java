package com.lonjoy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author luke
 * @date 22:59 2022/8/12
 * @desc 鉴权服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lonjoy.auth.provider.mapper")
public class LonJoyAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LonJoyAuthApplication.class, args);
    }

}
