package com.lonjoy.framework.rabbitmq.callback;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonjoy.framework.rabbitmq.MessageConsumer;
import com.lonjoy.framework.rabbitmq.constant.ConsumeCode;
import com.lonjoy.framework.rabbitmq.constant.MqCenterCode;
import com.lonjoy.framework.rabbitmq.model.RabbitMessage;
import com.lonjoy.framework.rabbitmq.model.ResponseData;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.util.concurrent.TimeUnit;

/**
 * @author luke
 * @date 2022/8/15 21:19
 * @desc
 **/

public class ReceiveConfirmListener implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(ReceiveConfirmListener.class);
    private final RabbitTemplate rabbitTemplate;
    private final int maxRetries;
    private long serviceNotFoundSleep = 30L;
    private long serviceTimeoutSleep = 5L;
    private final MessageConsumer messageConsumer;

    public ReceiveConfirmListener(MessageConsumer messageConsumer, RabbitTemplate rabbitTemplate, int maxRetries, long serviceNotFoundSleep, long serviceTimeoutSleep) {
        this.messageConsumer = messageConsumer;
        this.rabbitTemplate = rabbitTemplate;
        this.maxRetries = maxRetries;
        if (serviceNotFoundSleep > 0L) {
            this.serviceNotFoundSleep = serviceNotFoundSleep;
        }

        if (serviceTimeoutSleep > 0L) {
            this.serviceTimeoutSleep = serviceTimeoutSleep;
        }

    }

    public void onMessage(Message message, Channel channel) throws Exception {
        channel.basicQos(1);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            RabbitMessage rabbitMessage = (RabbitMessage)objectMapper.readValue(message.getBody(), RabbitMessage.class);
            if (rabbitMessage == null) {
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                return;
            }

            long messageId = rabbitMessage.getId();
            logger.debug("Message[{}] Exchange: {}, RoutingKey: {}, Queue: {}.", new Object[]{messageId, message.getMessageProperties().getReceivedExchange(), message.getMessageProperties().getReceivedRoutingKey(), message.getMessageProperties().getConsumerQueue()});
            ResponseData responseData = this.consumeMessage(rabbitMessage);
            logger.debug("Message[{}] ResponseData Code[{}, {}].", new Object[]{messageId, responseData.getCode(), responseData.getMsg()});
            if (responseData.getCode() == MqCenterCode.SUCCESS.getCode()) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                logger.debug("Message[{}] has been successfully consumed.", messageId);
            } else if (responseData.getCode() == MqCenterCode.MESSAGE_CONSUME_FAILED.getCode()) {
                Integer retriesHeader = (Integer)message.getMessageProperties().getHeaders().get("x-retries");
                if (retriesHeader == null) {
                    retriesHeader = 0;
                }

                if (retriesHeader < this.maxRetries) {
                    message.getMessageProperties().getHeaders().put("x-retries", retriesHeader + 1);
                    int priority = message.getMessageProperties().getPriority();
                    message.getMessageProperties().setPriority(priority - 1);
                    this.rabbitTemplate.send(message.getMessageProperties().getReceivedExchange(), message.getMessageProperties().getReceivedRoutingKey(), message, new CorrelationData(String.valueOf(rabbitMessage.getId())));
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    logger.debug("Message[{}] has been requeue[{}].", messageId, retriesHeader);
                } else {
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                    logger.debug("Message[{}] has been reject.", messageId);
                }
            } else if (responseData.getCode() == MqCenterCode.MESSAGE_CONSUMER_NOT_FOUND.getCode()) {
                logger.debug("Message[{}] has been requeue for consumer not found, sleep {} second.", messageId, this.serviceNotFoundSleep);
                this.process4error(message, channel, messageId, this.serviceNotFoundSleep);
            } else {
                logger.debug("Message[{}] has been requeue for time out, sleep {} second.", messageId, this.serviceTimeoutSleep);
                this.process4error(message, channel, messageId, this.serviceTimeoutSleep);
            }

            logger.debug("Message[{}] has been processed.", messageId);
        } catch (Exception var10) {
            logger.error("message consume error ", var10);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

    private void process4error(Message message, Channel channel, long messageId, long timeout) throws Exception {
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);

        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (Exception var8) {
        }

    }

    private ResponseData consumeMessage(RabbitMessage rabbitMessage) {
        try {
            ConsumeCode consumeCode = this.messageConsumer.consumeMessage(rabbitMessage);
            return consumeCode.getCode() == ConsumeCode.SUCCESS.getCode() ? new ResponseData(MqCenterCode.SUCCESS) : new ResponseData(MqCenterCode.MESSAGE_CONSUME_FAILED);
        } catch (Exception var3) {
            logger.error("consume message occur exception on " + this.messageConsumer.getClass().getCanonicalName(), var3);
            return new ResponseData(MqCenterCode.MESSAGE_CONSUME_FAILED);
        }
    }
}
