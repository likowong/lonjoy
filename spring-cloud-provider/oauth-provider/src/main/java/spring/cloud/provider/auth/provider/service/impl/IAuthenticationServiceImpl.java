package spring.cloud.provider.auth.provider.service.impl;

import org.springframework.stereotype.Service;
import spring.cloud.provider.auth.provider.service.IAuthenticationService;

/**
 * @author luke
 * @date 2021/1/24 19:44
 * @desc
 **/
@Service
public class IAuthenticationServiceImpl implements IAuthenticationService {
    @Override
    public boolean decide(String authentication, String url, String method) {
        return true;
    }
}
