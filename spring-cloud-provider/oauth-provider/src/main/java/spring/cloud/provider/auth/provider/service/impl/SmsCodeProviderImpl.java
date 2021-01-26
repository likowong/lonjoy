package spring.cloud.provider.auth.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.cloud.provider.auth.provider.service.SmsCodeProvider;

@Component
public class SmsCodeProviderImpl implements SmsCodeProvider {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String getSmsCode(String mobile, String businessType) {
        // 该类为mock, 目前暂时没有sms的服务
        return passwordEncoder.encode("123456");
    }
}
