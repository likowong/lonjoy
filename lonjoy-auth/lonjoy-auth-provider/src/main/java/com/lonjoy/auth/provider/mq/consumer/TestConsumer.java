package com.lonjoy.auth.provider.mq.consumer;

import com.lonjoy.auth.provider.constant.MqConstant;
import com.lonjoy.framework.rabbitmq.MessageConsumer;
import com.lonjoy.framework.rabbitmq.annotation.MqCenterListenerEndpoint;
import com.lonjoy.framework.rabbitmq.constant.ConsumeCode;
import com.lonjoy.framework.rabbitmq.model.RabbitMessage;

/**
 * @author luke
 * @date 2022/8/15 22:51
 * @desc
 **/
@MqCenterListenerEndpoint(listenQueue = MqConstant.TEST_QUEUE)
public class TestConsumer implements MessageConsumer {
    /**
     * 消费
     *
     * @param rabbitMessage
     */
    @Override
    public ConsumeCode consumeMessage(RabbitMessage rabbitMessage) {
        System.out.println("消费：" + rabbitMessage.getMessageBody());
        return ConsumeCode.SUCCESS;
    }
}
