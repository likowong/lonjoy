package com.lonjoy.auth.api.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luke
 * @date 2022/8/12 23:04
 * @desc 密码登录参数
 **/
@Data
public class PwdLoginParams implements Serializable {
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 密码
     */
    private String accountPwd;
}
