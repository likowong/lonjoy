package com.nacos.client.web;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.nacos.client.service.NacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    private NacosService nacosService;

    @Value(value = "${useLocalCache:1234}")
    private String useLocalCache;

    public void setUseLocalCache(String useLocalCache) {
        this.useLocalCache = useLocalCache;
    }

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public String get() {
        return useLocalCache;
    }


    @RequestMapping(value = "/hello", method = GET)
    @ResponseBody
    public String hello(String serviceName){
        return nacosService.hello(serviceName);
    }
}
