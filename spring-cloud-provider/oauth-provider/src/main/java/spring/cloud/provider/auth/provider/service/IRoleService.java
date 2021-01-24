package spring.cloud.provider.auth.provider.service;


import org.springframework.stereotype.Service;
import spring.cloud.provider.auth.provider.entity.Role;

import java.util.Set;

@Service
public interface IRoleService {

    Set<Role> queryUserRolesByUserId(long userId);

}
