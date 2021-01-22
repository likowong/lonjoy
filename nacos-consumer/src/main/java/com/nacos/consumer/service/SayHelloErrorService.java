package com.nacos.consumer.service;

import org.springframework.stereotype.Component;

/**
 * @author luke
 * @date 2020/11/27 0027 12:57
 * @desc feign客户端
 **/
@Component
public class SayHelloErrorService implements SayHelloService {

    @Override
    public String hello(String serviceName) {
        return serviceName + "被熔断了";

    }
}
