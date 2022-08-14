package com.lonjoy.external.external.open.client.service;

import com.lonjoy.common.ResResult;
import com.lonjoy.external.external.open.api.params.SendSmsParams;
import com.lonjoy.external.external.open.api.service.SmsInterface;
import com.lonjoy.external.external.open.client.constant.Constant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import static com.lonjoy.common.ApplicationResponseCode.SERVICE_FAILED;


/**
 * @author luke
 * @date 2022/8/12 23:09
 * @desc 用户认证服务
 **/
@FeignClient(name = Constant.projectName, contextId = "accountRemoteInterface", fallback = SmsRemoteInterface.AuthRemoteInterfaceHystrix.class)
public interface SmsRemoteInterface extends SmsInterface {
    @Component
    public static class AuthRemoteInterfaceHystrix implements SmsRemoteInterface {
        /**
         * @param sendSmsParams
         * @return ResResult
         * @author luke
         * @date 23:52 2022/8/12
         * @desc 发送短信
         */
        @Override
        public ResResult sendSmsMsg(SendSmsParams sendSmsParams) {
            return ResResult.fail(SERVICE_FAILED);
        }
    }
}
