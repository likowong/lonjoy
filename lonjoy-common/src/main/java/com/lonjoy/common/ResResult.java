package com.lonjoy.common;


import com.lonjoy.common.page.ResPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import static com.lonjoy.common.ApplicationResponseCode.SYS_SUCCESS;


/**
 * 响应报文消息
 *
 * @author liko wang
 */
@ApiModel(value = "ResBody对象", description = "响应报文消息")
public class ResResult<T> implements Serializable {
    /**
     * 请求响应状态
     */
    @ApiModelProperty(value = "请求响应状态", example = "SYS_OK")
    private String status;
    /**
     * 请求响应码
     */
    @ApiModelProperty(value = "请求响应码", example = "1")
    private int code;
    /**
     * 请求响应消息
     */
    @ApiModelProperty(value = "请求响应码", example = "请求成功")
    private String message;
    /**
     * 请求响应数据对象
     */
    @ApiModelProperty(value = "请求响应数据对象")
    private T data;

    /**
     * 请求响应分页数据对象
     */
    @ApiModelProperty(value = "请求响应分页数据对象")
    private ResPage<T> page;

    public ResResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResPage getPage() {
        return page;
    }

    public void setPage(ResPage page) {
        this.page = page;
    }

    public static ResResult success() {
        return new ResResult().buildSuccessResBody(null, null, SYS_SUCCESS);
    }

    public static ResResult success(Object content) {
        return new ResResult().buildSuccessResBody(content, null, SYS_SUCCESS);
    }

    public static ResResult success(ResPage page) {
        return new ResResult().buildSuccessResBody(null, page, SYS_SUCCESS);
    }

    public ResResult success(Object content, ResPage<T> page) {
        return new ResResult().buildSuccessResBody(content, page, SYS_SUCCESS);
    }

    public boolean isSuccess() {
        return code == 1 ? true : false;
    }

    public ResResult<T> buildSuccessResBody(T content, ResPage<T> page, ApplicationResponseCode successConst) {
        ResResult res = new ResResult();
        res.setStatus(successConst.getStatus());
        res.setCode(successConst.getCode());
        res.setMessage(successConst.getMessage());

        if (content != null) {
            res.setData(content);
        }

        if (page != null) {
            res.setPage(page);
        }
        return res;
    }

    public static ResResult fail() {
        return fail(ApplicationResponseCode.SYS_FAILED);
    }

    /**
     * 构建失败报文头
     */
    public static ResResult fail(ApplicationResponseCode errorConst) {
        ResResult res = new ResResult();
        res.setStatus(errorConst.getStatus());
        res.setCode(errorConst.getCode());
        res.setMessage(errorConst.getMessage());
        return res;
    }

    /**
     * 构建自定义错误文本信息
     */
    public static ResResult buildFailTextResBody(int code, String message) {
        ResResult res = new ResResult();
        res.setStatus(ApplicationResponseCode.SYS_FAILED.getStatus());
        res.setCode(code);
        res.setMessage(message);
        return res;
    }
}
