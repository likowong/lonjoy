package com.lonjoy.framework.rabbitmq.model;

import com.lonjoy.framework.rabbitmq.constant.MqCenterCode;
import lombok.Data;

/**
 * @author luke
 * @date 2022/8/15 21:27
 * @desc
 **/
@Data
public class ResponseData {
    private int code;
    private String msg;

    public ResponseData(MqCenterCode mqCenterCode){
        this.code = mqCenterCode.getCode();
        this.msg = mqCenterCode.getMessage();
    }
}
