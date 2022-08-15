package com.lonjoy.framework.rabbitmq;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonjoy.common.ApplicationException;
import com.lonjoy.common.ApplicationResponseCode;
import com.lonjoy.framework.rabbitmq.model.Destination;
import com.lonjoy.framework.rabbitmq.model.RabbitMessage;
import com.lonjoy.framework.rabbitmq.model.SendMessageRequest;
import com.lonjoy.framework.rabbitmq.service.AutoDeclareStrategy;
import com.lonjoy.framework.rabbitmq.service.MqCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.sql.Timestamp;

/**
 * @author luke
 * @date 2022/8/15 21:47
 * @desc
 **/

public class MqClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqClient.class);
    private static MqCenterService mqCenterService;
    private static ObjectMapper objectMapper;
    private static AutoDeclareStrategy autoDeclareStrategy;
    static Snowflake snowflake = IdUtil.createSnowflake(1, 1);


    public MqClient() {
    }

    public static void setMqCenterService(MqCenterService mqCenterService) {
        MqClient.mqCenterService = mqCenterService;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        MqClient.objectMapper = objectMapper;
    }

    public static void setAutoDeclareStrategy(AutoDeclareStrategy autoDeclareStrategy) {
        if (autoDeclareStrategy == null) {
            MqClient.autoDeclareStrategy = OnceAutoDeclareStrategy.INSTANCE;
        } else {
            MqClient.autoDeclareStrategy = autoDeclareStrategy;
        }

    }

    public static long sendMessage(String exchange, String routing, String queue, Object content) throws JsonProcessingException {
        return sendMessage("", exchange, routing, queue, content);
    }

    public static long sendMessage(String host, String exchange, String routing, String queue, Object content) throws JsonProcessingException {
        SendMessageRequest request = new SendMessageRequest();
        request.setContent(content);
        request.setExchange(exchange);
        request.setRoutingKey(routing);
        request.setQueue(queue);
        request.setHost(host);
        return sendMessage(request);
    }

    public static long sendMessage(SendMessageRequest request) throws JsonProcessingException {
        return sendMessage(request, autoDeclareStrategy);
    }

    public static long sendMessage(SendMessageRequest request, AutoDeclareStrategy autoDeclareStrategy) throws JsonProcessingException {
        Assert.hasText(request.getExchange(), "exchange name must not be null");
        Assert.hasText(request.getRoutingKey(), "routing key must not be null");
        Assert.hasText(request.getQueue(), "queue must not be null");
        Assert.notNull(request.getContent(), "content must not be null");
        String jsonContent = objectMapper.writeValueAsString(request.getContent());

        try {
            Destination destination = new Destination(request.getExchange(), request.getRoutingKey(), request.getQueue());
            RabbitMessage rabbitMessage = new RabbitMessage();
            rabbitMessage.setId(snowflake.nextId());
            rabbitMessage.setDestination(destination);
            rabbitMessage.setMessageBody(jsonContent);
            rabbitMessage.setCreationDate(new Timestamp(System.currentTimeMillis()));
            rabbitMessage.setFrom(SpringUtils.getApplicationName());
            rabbitMessage.setPriority(request.getPriority());
            rabbitMessage.setAutoDeclare(autoDeclareStrategy.isAutoDeclare(request));
            mqCenterService.sendMessage(request.getHost(), request.getExchange(), request.getRoutingKey(), request.getQueue(), rabbitMessage);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("[MqClient][sendMessage] id: {}, body: {} saved to ehcache and queue successfully", rabbitMessage.getId(), rabbitMessage.getMessageBody());
            }

            return rabbitMessage.getId();
        } catch (ApplicationException var5) {
            autoDeclareStrategy.sendMessageFailed(request, var5);
            throw var5;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            autoDeclareStrategy.sendMessageFailed(request, ex);
            throw new ApplicationException(new ApplicationResponseCode(-1, "mqError", ex.getMessage()));

        }
    }

    static {
        autoDeclareStrategy = OnceAutoDeclareStrategy.INSTANCE;
    }
}
