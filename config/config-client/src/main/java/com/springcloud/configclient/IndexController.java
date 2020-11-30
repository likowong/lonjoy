package com.springcloud.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class IndexController {

    @Value("${demo}")
    private String book;

    @RequestMapping("/getDemoConfig")
    public String getDemoConfig(){
        return book;
    }
}
