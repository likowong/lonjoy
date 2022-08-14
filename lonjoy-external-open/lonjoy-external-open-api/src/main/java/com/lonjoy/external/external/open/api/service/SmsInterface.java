package com.lonjoy.external.external.open.api.service;

import com.lonjoy.common.ResResult;
import com.lonjoy.external.external.open.api.params.SendSmsParams;
import org.springframework.web.bind.annotation.*;

/**
 * @author luke
 * @date 2022/8/12 23:07
 * @desc 短信服务
 **/
public interface SmsInterface {
    /**
     * @param sendSmsParams
     * @return ResResult
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 发送短信
     */
    @PostMapping(value = "/account/getAccountByUserId")
    ResResult sendSmsMsg(@RequestBody SendSmsParams sendSmsParams);
}
