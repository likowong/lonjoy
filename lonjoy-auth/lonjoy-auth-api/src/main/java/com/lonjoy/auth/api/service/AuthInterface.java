package com.lonjoy.auth.api.service;

import com.lonjoy.auth.api.params.PermissionParams;
import com.lonjoy.auth.api.vo.UserInfo;
import com.lonjoy.auth.api.params.PwdLoginParams;
import com.lonjoy.common.ResResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author luke
 * @date 2022/8/12 23:07
 * @desc 用户认证服务
 **/
public interface AuthInterface {
    /**
     * @param pwdLoginParams
     * @desc 用户密码登录
     */
    @PostMapping(value = "/oauth/pwdLogin")
    ResResult<UserInfo> pwdLogin(@RequestBody PwdLoginParams pwdLoginParams);

    /**
     * @param permissionParams
     * @return {@link null}
     * @author luke
     * @date 0:00 2022/8/13
     * @desc 是否有权限
     */
    @PostMapping(value = "/oauth/hasPermission")
    ResResult<Boolean> hasPermission(@RequestBody PermissionParams permissionParams);

    /**
     * @param authentication
     * @return UserInfo
     * @author luke
     * @date 23:52 2022/8/12
     * @desc 通过token获取用户信息
     */
    @GetMapping(value = "/oauth/getUserInfo")
    ResResult<UserInfo> getUserInfo(@RequestHeader(value = "Authorization") String authentication);


}
