package com.lonjoy.external.open.provider.controller;

import com.lonjoy.common.ResResult;
import com.lonjoy.external.external.open.api.params.SendSmsParams;
import com.lonjoy.external.external.open.api.service.SmsInterface;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luke
 * @date 2022/8/12 23:20
 * @desc 授权控制层
 **/
@RestController
public class SmsController implements SmsInterface {
    /**
     * @param sendSmsParams
     * @return ResResult
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 发送短信
     */
    @PostMapping(value = "/account/getAccountByUserId")
    public ResResult sendSmsMsg(SendSmsParams sendSmsParams) {
        return null;
    }
}
