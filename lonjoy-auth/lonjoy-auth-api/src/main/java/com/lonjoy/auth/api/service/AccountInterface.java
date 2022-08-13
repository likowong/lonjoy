package com.lonjoy.auth.api.service;

import com.lonjoy.auth.api.params.RegisterAccountParams;
import com.lonjoy.auth.api.vo.UserInfo;
import com.lonjoy.common.ResResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author luke
 * @date 2022/8/12 23:07
 * @desc 用户认证服务
 **/
public interface AccountInterface {
    /**
     * @param userId
     * @return UserInfo
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 通过userId获取用户信息
     */
    @GetMapping(value = "/account/getAccountByUserId")
    ResResult<UserInfo> getAccount(@RequestParam(value = "userId") Long userId);

    /**
     * @param registerAccountParams
     * @return UserInfo
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 注册用户
     */
    @PostMapping(value = "/account/register")
    ResResult<UserInfo> registerAccount(@RequestBody RegisterAccountParams registerAccountParams);
}
