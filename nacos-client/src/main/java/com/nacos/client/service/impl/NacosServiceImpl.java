package com.nacos.client.service.impl;

import com.nacos.client.service.NacosService;
import org.springframework.stereotype.Service;

/**
 * @author luke
 * @date 2020/12/24 0024 14:25
 * @desc demo
 **/
@Service
public class NacosServiceImpl implements NacosService {
    @Override
    public String hello(String serviceName) {
        return "nacos server test " + serviceName;
    }
}
