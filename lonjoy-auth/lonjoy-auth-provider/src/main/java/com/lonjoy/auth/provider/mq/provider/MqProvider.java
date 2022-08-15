package com.lonjoy.auth.provider.mq.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lonjoy.auth.provider.constant.MqConstant;
import com.lonjoy.auth.provider.mq.model.TestMq;
import com.lonjoy.framework.rabbitmq.MqClient;
import com.lonjoy.framework.rabbitmq.model.SendMessageRequest;

/**
 * @author luke
 * @date 2022/8/15 22:53
 * @desc
 **/
public class MqProvider {

    public static void sendTestMsg(TestMq testMq) throws JsonProcessingException {
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setExchange(MqConstant.TEST_EXCHANGE);
        sendMessageRequest.setRoutingKey(MqConstant.TEST_ROUTING_KEY);
        sendMessageRequest.setQueue(MqConstant.TEST_QUEUE);
        sendMessageRequest.setContent(testMq);
        MqClient.sendMessage(sendMessageRequest);
    }
}
