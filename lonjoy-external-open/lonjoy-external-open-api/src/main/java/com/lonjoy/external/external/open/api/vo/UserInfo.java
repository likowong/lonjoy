package com.lonjoy.external.external.open.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luke
 * @date 2022/8/12 23:02
 * @desc 用户信息
 **/
@Data
public class UserInfo implements Serializable {
    private String token;
    private String name;
    private String openId;
}
