package spring.cloud.provider.auth.provider.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.cloud.provider.auth.provider.dao.RoleMapper;
import spring.cloud.provider.auth.provider.entity.Role;
import spring.cloud.provider.auth.provider.service.IRoleService;

import java.util.Set;


@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> queryUserRolesByUserId(long userId) {
        return roleMapper.queryByUserId(userId);
    }

}
