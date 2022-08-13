package com.lonjoy.auth.client.service;

import com.lonjoy.auth.api.params.RegisterAccountParams;
import com.lonjoy.auth.api.service.AccountInterface;
import com.lonjoy.auth.api.vo.UserInfo;
import com.lonjoy.auth.client.constant.AuthConstant;
import com.lonjoy.common.ResResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import static com.lonjoy.common.ApplicationResponseCode.SERVICE_FAILED;

/**
 * @author luke
 * @date 2022/8/12 23:09
 * @desc 用户认证服务
 **/
@FeignClient(name = AuthConstant.projectName, contextId = "accountRemoteInterface", fallback = AccountRemoteInterface.AuthRemoteInterfaceHystrix.class)
public interface AccountRemoteInterface extends AccountInterface {
    @Component
    public static class AuthRemoteInterfaceHystrix implements AccountRemoteInterface {
        /**
         * @param userId
         * @return UserInfo
         * @author luke
         * @date 23:52 2022/8/12
         * @desc 通过userId获取用户信息
         */
        @Override
        public ResResult<UserInfo> getAccount(Long userId) {
            return ResResult.fail(SERVICE_FAILED);
        }

        /**
         * @param registerAccountParams
         * @return UserInfo
         * @author luke
         * @date 23:52 2022/8/12
         * @desc 注册用户
         */
        @Override
        public ResResult<UserInfo> registerAccount(RegisterAccountParams registerAccountParams) {
            return ResResult.fail(SERVICE_FAILED);
        }
    }
}
