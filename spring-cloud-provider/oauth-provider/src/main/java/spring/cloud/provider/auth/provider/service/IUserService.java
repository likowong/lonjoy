package spring.cloud.provider.auth.provider.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spring.cloud.provider.auth.provider.entity.User;

@Service
public interface IUserService {

    @Cacheable(value = "#id")
    User getByUniqueId(String username);
}
