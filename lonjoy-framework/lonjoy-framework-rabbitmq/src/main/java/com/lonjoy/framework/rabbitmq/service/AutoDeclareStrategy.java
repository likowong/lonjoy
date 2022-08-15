package com.lonjoy.framework.rabbitmq.service;

import com.lonjoy.framework.rabbitmq.model.SendMessageRequest;

/**
 * @author luke
 * @date 2022/8/15 21:45
 * @desc
 **/
public interface AutoDeclareStrategy {
    boolean isAutoDeclare(SendMessageRequest var1);

    void sendMessageFailed(SendMessageRequest var1, Exception var2);
}

