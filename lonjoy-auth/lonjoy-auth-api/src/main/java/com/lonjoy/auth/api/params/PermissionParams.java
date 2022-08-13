package com.lonjoy.auth.api.params;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luke
 * @date 2022/8/12 23:59
 * @desc 是否有权限
 **/
@Data
public class PermissionParams implements Serializable {
    /**
     * 授权token
     */
    private String token;
    /**
     * 访问url
     */
    private String url;
    /**
     * 客户端类型
     */
    private String clientType;
}
