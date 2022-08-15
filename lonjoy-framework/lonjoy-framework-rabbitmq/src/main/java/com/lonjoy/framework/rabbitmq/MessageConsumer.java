package com.lonjoy.framework.rabbitmq;

import com.lonjoy.framework.rabbitmq.constant.ConsumeCode;
import com.lonjoy.framework.rabbitmq.model.RabbitMessage;

/**
 * @author luke
 * @date 2022/8/15 21:07
 * @desc 消费
 **/
public interface MessageConsumer {
    /**
     * 消费
     */
    ConsumeCode consumeMessage(RabbitMessage var1);
}
