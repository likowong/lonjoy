package com.lonjoy.framework.rabbitmq.startup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonjoy.framework.rabbitmq.MessageConsumer;
import com.lonjoy.framework.rabbitmq.MqClient;
import com.lonjoy.framework.rabbitmq.annotation.MqCenterListenerEndpoint;
import com.lonjoy.framework.rabbitmq.config.CallBackProperty;
import com.lonjoy.framework.rabbitmq.config.MQCenterClientProperty;
import com.lonjoy.framework.rabbitmq.service.AutoDeclareStrategy;
import com.lonjoy.framework.rabbitmq.service.MqCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luke
 * @date 2022/8/15 19:22
 * @desc mq启动
 **/
@Component
public class MqClientInitialization implements ApplicationRunner, Ordered {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqClientInitialization.class);
    @Value("${spring.application.name}")
    private String serviceName;
    @Autowired
    private MqCenterService mqCenterService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    @Qualifier("jackson2JsonMessageConverter")
    private MessageConverter jackson2JsonMessageConverter;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MQCenterClientProperty mqCenterClientProperty;
    @Autowired
    private CallBackProperty callBackProperty;

    private static ConcurrentHashMap<String, MessageConsumer> MessageConsumerConcurrentHashMap = new ConcurrentHashMap();

    public MqClientInitialization() {
    }

    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("[MqClientInitialization][run]初始化 MQ 服务器");
        if (this.connectionFactory instanceof AbstractConnectionFactory) {
            ((AbstractConnectionFactory) this.connectionFactory).getRabbitConnectionFactory().setHandshakeTimeout(this.mqCenterClientProperty.getHandshakeTimeout());
        }
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(this.jackson2JsonMessageConverter);
        if (callBackProperty.getConfirmCallBackListener() != null) {
            Class<?> aClass = Class.forName(callBackProperty.getConfirmCallBackListener());
            Object confirmCallback = aClass.newInstance();
            rabbitTemplate.setConfirmCallback((RabbitTemplate.ConfirmCallback) confirmCallback);
        }
        if (callBackProperty.getReturnCallBackListener() != null) {
            Class<?> aClass = Class.forName(callBackProperty.getReturnCallBackListener());
            Object returnCallback = aClass.newInstance();
            rabbitTemplate.setReturnCallback((RabbitTemplate.ReturnCallback) returnCallback);
        }
        rabbitAdmin.setIgnoreDeclarationExceptions(true);

        Map<String, Object> messageConsumerBeanMap = this.applicationContext.getBeansWithAnnotation(MqCenterListenerEndpoint.class);
        Iterator iterator = messageConsumerBeanMap.values().iterator();

        while (iterator.hasNext()) {
            Object messageConsumerBean = iterator.next();
            MessageConsumer messageConsumer = (MessageConsumer) messageConsumerBean;
            this.mqCenterService.registerListener(messageConsumer);
        }

        iterator = this.applicationContext.getBeansOfType(AutoDeclareStrategy.class).values().iterator();

        while (iterator.hasNext()) {
            AutoDeclareStrategy autoDeclare = (AutoDeclareStrategy) iterator.next();
            MqClient.setAutoDeclareStrategy(autoDeclare);
        }

        MqClient.setMqCenterService(this.mqCenterService);
        MqClient.setObjectMapper(this.objectMapper);
    }

    public int getOrder() {
        return this.mqCenterClientProperty.getStartup().getOrder();
    }

    public static ConcurrentHashMap<String, MessageConsumer> getMessageConsumerConcurrentHashMap() {
        return MessageConsumerConcurrentHashMap;
    }

    public static MessageConsumer getMessageConsumerByCode(String code) {
        return (MessageConsumer) MessageConsumerConcurrentHashMap.get(code);
    }
}
