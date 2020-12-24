package com.nacos.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author luke
 * @date 2020/11/27 0027 12:57
 * @desc feign客户端
 **/
@FeignClient(value = "nacos-client")
public interface SayHelloService {
    /**
     * @return [String]
     * @author luke
     * @date 15:15 2020/11/27 0027
     * @desc feign调用
     * @Param null
     */
    @RequestMapping(value = "/hello", method = GET)
    @ResponseBody
    String hello(@RequestParam String serviceName);
}
