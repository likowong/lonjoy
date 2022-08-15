package com.lonjoy.auth.provider.mq.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luke
 * @date 2022/8/15 22:58
 * @desc
 **/
@Data
public class TestMq implements Serializable {
    private String msg;
    public TestMq(String msg){
        this.msg = msg;
    }
}
