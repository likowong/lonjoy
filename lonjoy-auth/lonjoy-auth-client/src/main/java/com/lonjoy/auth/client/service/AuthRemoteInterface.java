package com.lonjoy.auth.client.service;

import com.lonjoy.auth.api.params.PermissionParams;
import com.lonjoy.auth.api.params.PwdLoginParams;
import com.lonjoy.auth.api.service.AuthInterface;
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
@FeignClient(name = AuthConstant.projectName,contextId = "authRemoteInterface",fallback = AuthRemoteInterface.AuthRemoteInterfaceHystrix.class)
public interface AuthRemoteInterface extends AuthInterface {
    @Component
    public static class AuthRemoteInterfaceHystrix implements AuthRemoteInterface {
        @Override
        public ResResult pwdLogin(PwdLoginParams pwdLoginParams) {
            return ResResult.fail(SERVICE_FAILED);
        }

        @Override
        public ResResult<UserInfo> getUserInfo(String token) {
            return ResResult.fail(SERVICE_FAILED);
        }

        @Override
        public ResResult<Boolean> hasPermission(PermissionParams permissionParams) {
            return ResResult.fail(SERVICE_FAILED);
        }
    }
}
