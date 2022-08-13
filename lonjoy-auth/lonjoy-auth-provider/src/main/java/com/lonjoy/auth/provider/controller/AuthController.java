package com.lonjoy.auth.provider.controller;

import com.lonjoy.auth.api.params.PermissionParams;
import com.lonjoy.auth.api.params.PwdLoginParams;
import com.lonjoy.auth.api.service.AuthInterface;
import com.lonjoy.auth.api.vo.UserInfo;
import com.lonjoy.common.ResResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author luke
 * @date 2022/8/12 23:20
 * @desc 授权控制层
 **/
@RestController
public class AuthController implements AuthInterface {
    /**
     * @param pwdLoginParams
     * @return UserInfo
     * @author luke
     * @date 23:22 2022/8/12
     * @desc 密码登录
     */
    @Override
    @PostMapping(value = "/oauth/pwdLogin")
    public ResResult<UserInfo> pwdLogin(@RequestBody PwdLoginParams pwdLoginParams) {
        UserInfo userInfo = new UserInfo();
        userInfo.setToken("xzcxzczad12312");
        userInfo.setName("张三");
        userInfo.setOpenId("23123asadsasd");
        return ResResult.success(userInfo);
    }

    @Override
    @GetMapping(value = "/oauth/getUserInfo")
    public ResResult<UserInfo> getUserInfo(@RequestHeader(value = "Authorization") String authentication) {
        UserInfo userInfo = new UserInfo();
        userInfo.setToken("xzcxzczad12312");
        userInfo.setName("张三");
        userInfo.setOpenId("23123asadsasd");
        return ResResult.success(userInfo);
    }

    @Override
    @PostMapping(value = "/oauth/hasPermission")
    public ResResult<Boolean> hasPermission(@RequestBody PermissionParams permissionParams) {
        return ResResult.success(true);
    }
}
