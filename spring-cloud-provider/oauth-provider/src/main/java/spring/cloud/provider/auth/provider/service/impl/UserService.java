package spring.cloud.provider.auth.provider.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.cloud.provider.auth.provider.dao.UserMapper;
import spring.cloud.provider.auth.provider.entity.User;
import spring.cloud.provider.auth.provider.service.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUniqueId(String username) {
        return userMapper.getByUsername(username);
    }
}
