package com.nacos.client.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
    @SentinelResource(value = "NacosServiceImpl#hello", defaultFallback = "bonjourFallback")
    public String hello(String serviceName) throws InterruptedException {
        Thread.sleep(5000);
        return "nacos server test " + serviceName;
    }

    public String bonjourFallback(Throwable t) {
        if (BlockException.isBlockException(t)) {
            return "Blocked by Sentinel: " + t.getClass().getSimpleName();
        }
        return "Oops, failed: " + t.getClass().getCanonicalName();
    }
}
