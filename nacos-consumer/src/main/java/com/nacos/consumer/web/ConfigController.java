package com.nacos.consumer.web;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.nacos.consumer.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author luke
 * @date 2020/12/24 0024 11:34
 * @desc
 **/
@Controller
@RefreshScope
public class ConfigController {

    @Autowired
    private SayHelloService sayHelloService;

    @Value(value = "${demo:1234}")
    private String demo;

    public void setUseLocalCache(String demo) {
        this.demo = demo;
    }

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return demo;
    }

    @RequestMapping(value = "/sayHello", method = GET)
    @ResponseBody
    public String sayHello() {
        return sayHelloService.hello("sayHello");
    }
}
