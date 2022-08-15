package com.lonjoy.framework.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luke
 * @date 2022/8/15 21:57
 * @desc
 **/
@ConfigurationProperties("mq.center.coll.back")
public class CallBackProperty {
    /**
     * 确认回调
     */
    private String confirmCallBackListener;
    /**
     * 确认回调
     */
    private String returnCallBackListener;

    public String getConfirmCallBackListener() {
        return confirmCallBackListener;
    }

    public void setConfirmCallBackListener(String confirmCallBackListener) {
        this.confirmCallBackListener = confirmCallBackListener;
    }

    public String getReturnCallBackListener() {
        return returnCallBackListener;
    }

    public void setReturnCallBackListener(String returnCallBackListener) {
        this.returnCallBackListener = returnCallBackListener;
    }
}
