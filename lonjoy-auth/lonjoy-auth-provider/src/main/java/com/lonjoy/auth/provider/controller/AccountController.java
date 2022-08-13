package com.lonjoy.auth.provider.controller;

import com.lonjoy.auth.api.params.RegisterAccountParams;
import com.lonjoy.auth.api.service.AccountInterface;
import com.lonjoy.auth.api.vo.UserInfo;
import com.lonjoy.auth.provider.service.AccountService;
import com.lonjoy.common.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author luke
 * @date 2022/8/12 23:20
 * @desc 授权控制层
 **/
@RestController
public class AccountController implements AccountInterface {
    @Autowired
    private AccountService accountService;

    /**
     * @param userId
     * @return UserInfo
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 通过userId获取用户信息
     */
    @Override
    @GetMapping(value = "/account/getAccountByUserId")
    public ResResult<UserInfo> getAccount(@RequestParam(value = "userId") Long userId) {
        return ResResult.success(accountService.getAccount(userId));
    }

    /**
     * @param registerAccountParams
     * @return UserInfo
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 注册用户
     */
    @Override
    @PostMapping(value = "/account/register")
    public ResResult registerAccount(@RequestBody @Valid RegisterAccountParams registerAccountParams) {
        accountService.registerAccount(registerAccountParams);
        return ResResult.success();
    }
}
