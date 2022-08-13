package com.lonjoy.common;

import java.io.Serializable;

/**
 * @author liko.wang
 * @Date 2019/12/18/018 14:20
 * @Description 全局响应码
 **/
public class ApplicationResponseCode implements Serializable {

    private static final long serialVersionUID = 2862665858875203182L;

    /**
     * 业务错误状态码
     */
    public static final int BIZ_ERROR_STATUS = 0;

    /**
     * 通用业务异常错误码
     */
    private static final String SYS_ERROR_CODE = "SYS_ERROR";
    private static final String SYS_OK = "SYS_OK";
    private static final String SYS_NOT_LOGIN = "SYS_NOT_LOGIN";
    private static final String SERVICE_NO_FOUND = "SERVICE_NO_FOUND";
    private static final String SERVICE_INVOKE_ERROR = "SERVICE_INVOKE_ERROR";
    private static final String NOT_PERMISSION = "NO_PERMISSION";
    private static final String TOKEN_IS_EMPTY = "TOKEN_IS_EMPTY";
    private static final String CLIENT_TYPE_IS_EMPTY = "CLIENT_TYPE_IS_EMPTY";

    /**
     * 构件通用业务错误对象
     */
    public static ApplicationResponseCode buildBizErrorConst(String errMsg) {
        return new ApplicationResponseCode(SYS_ERROR_CODE, errMsg);
    }

    /*************************************注意：非系统异常不允许自定义status**************************/
    public static final ApplicationResponseCode SYS_FAILED = new ApplicationResponseCode(0, SYS_ERROR_CODE, "系统太忙了，请稍后再试！");
    public static final ApplicationResponseCode SYS_SUCCESS = new ApplicationResponseCode(1, SYS_OK,  "请求成功！");
    public static final ApplicationResponseCode SYS_NO_LOGIN = new ApplicationResponseCode(2, SYS_NOT_LOGIN, "未登录!");
    public static final ApplicationResponseCode SERVICE_NOT_EXIST = new ApplicationResponseCode(3, SERVICE_NO_FOUND,"服务未找到!");
    public static final ApplicationResponseCode SERVICE_FAILED = new ApplicationResponseCode(4, SERVICE_INVOKE_ERROR, "服务调用失败!");
    public static final ApplicationResponseCode NO_PERMISSION = new ApplicationResponseCode(5, NOT_PERMISSION, "没有权限!");
    public static final ApplicationResponseCode TOKEN_IS_EMPTY_CODE = new ApplicationResponseCode(6, TOKEN_IS_EMPTY, "token为空!");
    public static final ApplicationResponseCode CLIENT_TYPE_IS_EMPTY_CODE = new ApplicationResponseCode(7, CLIENT_TYPE_IS_EMPTY, "客户端类型为空");


    /**************************************业务异常，可在子类定义自定义*******************************/
    public static final ApplicationResponseCode EXAMPLE_EXCEPTION = new ApplicationResponseCode("EXAMPLE_EXCEPTION", "异常自定义示例%s");

    private String status;
    private int code;
    private String message;

    public ApplicationResponseCode(int code, String status, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ApplicationResponseCode(String status, String message) {
        this.status = SYS_OK;
        this.code = BIZ_ERROR_STATUS;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplicationResponseCode changeMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 替换提示信息中的转换符
     */
    public ApplicationResponseCode format(String... formatArgs) {
        if (formatArgs != null && formatArgs.length > 0) {
            this.message = String.format(this.message, (Object[]) formatArgs);
        }

        return this;
    }
}
