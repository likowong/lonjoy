package spring.cloud.provider.auth.provider.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import spring.cloud.provider.auth.provider.entity.Resource;
import spring.cloud.provider.auth.provider.service.IResourceService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luke
 * @date 2021/1/24 19:45
 * @desc
 **/
@DubboService
public class IResourceServiceImpl implements IResourceService {
    @Override
    public Set<Resource> findAll() {
        return new HashSet<>();
    }

    @Override
    public Set<Resource> queryByRoleCodes(String[] roleCodes) {
        return new HashSet<>();
    }
}
