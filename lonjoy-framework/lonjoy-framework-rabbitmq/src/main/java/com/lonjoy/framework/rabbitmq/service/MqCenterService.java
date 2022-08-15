package com.lonjoy.framework.rabbitmq.service;

import com.lonjoy.common.ApplicationException;
import com.lonjoy.common.ApplicationResponseCode;
import com.lonjoy.framework.rabbitmq.MessageConsumer;
import com.lonjoy.framework.rabbitmq.annotation.MqCenterListenerEndpoint;
import com.lonjoy.framework.rabbitmq.callback.ReceiveConfirmListener;
import com.lonjoy.framework.rabbitmq.config.MQCenterClientProperty;
import com.lonjoy.framework.rabbitmq.model.RabbitMessage;
import com.lonjoy.framework.rabbitmq.startup.MqClientInitialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luke
 * @date 2022/8/15 21:12
 * @desc mq服务
 **/
@Component
public class MqCenterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqCenterService.class);
    public static final String X_MAX_PRIORITY_HEADER = "x-max-priority";
    public static final int DEFAULT_MAX_PRIORITY = 100;
    public static final int DEFAULT_PRIORITY = 50;
    public static final String X_RETRIES_HEADER = "x-retries";
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    public static final String DLQ_ROUTING_EXCHANGE = "lonjoy.dlq.exchange";
    public static final String DLQ_NAME = "lonjoy.dlq";
    @Value("${mqserver.consumer.max.retries:10}")
    private int maxRetries;
    @Value("${mqserver.consumer.sleep.service-not-found:30}")
    private long serviceNotFoundSleep;
    @Value("${mqserver.consumer.sleep.timeout:5}")
    private long serviceTimeoutSleep;
    @Value("${spring.rabbitmq.listener.simple.concurrency:1}")
    private int concurrentConsumers;
    @Value("${spring.rabbitmq.listener.simple.max-concurrency:10}")
    private int maxConcurrentConsumers;
    @Autowired
    private MQCenterClientProperty mqCenterClientProperty;
    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ConnectionFactory connectionFactory;

    public MqCenterService() {
    }

    public void sendMessage(String host, String exchange, String routingKey, String queue, final RabbitMessage message) {
        if(StringUtils.isEmpty(host)){
            host = "default";
        }

        if (message.isAutoDeclare()) {
            this.createDealLetter(rabbitAdmin);
            DirectExchange ex = new DirectExchange(exchange);
            rabbitAdmin.declareExchange(ex);
            Queue q = this.createQueue(queue);
            rabbitAdmin.declareQueue(q);
            rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(ex).with(routingKey));
        }

        rabbitTemplate.convertAndSend(exchange, routingKey, message, new MessagePostProcessor() {
            public Message postProcessMessage(Message msg) throws AmqpException {
                msg.getMessageProperties().setPriority(message.getPriority());
                msg.getMessageProperties().setCorrelationId(String.valueOf(message.getId()));
                msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return msg;
            }
        }, new CorrelationData(String.valueOf(message.getId())));
    }

    public void sendMessage(String exchange, String routingKey, String queue, RabbitMessage message) {
        this.sendMessage("", exchange, routingKey, queue, message);
    }

    public void registerListener(MessageConsumer messageConsumer) {
        Class<?> targetClass = AopUtils.getTargetClass(messageConsumer);
        MqCenterListenerEndpoint annotation = (MqCenterListenerEndpoint) AnnotationUtils.findAnnotation(targetClass, MqCenterListenerEndpoint.class);
        String queueName = annotation.listenQueue();
        String host = annotation.host();
        if(StringUtils.isEmpty(host)){
            host = "default";
        }
        int maxConcurrentConsumers = annotation.maxConcurrentConsumers();
        MqClientInitialization.getMessageConsumerConcurrentHashMap().put(queueName, messageConsumer);
        if (connectionFactory != null && rabbitAdmin != null && rabbitTemplate != null) {
            if (this.mqCenterClientProperty.getStartup().isAutoDeclare()) {
                rabbitAdmin.declareQueue(this.createQueue(queueName));
            }

            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
            container.setConnectionFactory(connectionFactory);
            container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
            container.setChannelTransacted(false);
            container.setQueueNames(new String[]{queueName});
            if (this.concurrentConsumers > 0) {
                container.setConcurrentConsumers(this.concurrentConsumers);
            }

            if (maxConcurrentConsumers > 0) {
                container.setMaxConcurrentConsumers(maxConcurrentConsumers);
            } else if (this.maxConcurrentConsumers > 0) {
                container.setMaxConcurrentConsumers(this.maxConcurrentConsumers);
            }

            container.setMessageListener(new ReceiveConfirmListener(messageConsumer, rabbitTemplate, this.maxRetries, this.serviceNotFoundSleep, this.serviceTimeoutSleep));
            container.start();
            LOGGER.debug("register successful, info: queueName:" + queueName);
        } else {
            throw new ApplicationException(new ApplicationResponseCode(-1, "mqError", "找不到 " + host + " 配置"));
        }
    }

    public void registerListener(String code, String queueName, String serverName, String path) {
        this.registerListener(code, queueName, serverName, path, this.maxConcurrentConsumers);
    }

    public void registerListener(String code, String queueName, String serverName, String path, Integer maxConcurrentConsumer) {
        this.registerListener(code, queueName, serverName, path, maxConcurrentConsumer, "default");
    }

    public void registerListener(String code, String queueName, String serverName, String path, Integer maxConcurrentConsumer, String host) {
        MessageConsumer messageConsumer = MqClientInitialization.getMessageConsumerByCode(code);
        if (messageConsumer == null) {
            LOGGER.info("[MqCenterService][registerListener]code：{}MessageConsumer is null", code);
        } else {
            if(StringUtils.isEmpty(host)){
                host = "default";
            }
            if (connectionFactory != null && rabbitAdmin != null ) {
                if (this.mqCenterClientProperty.getStartup().isAutoDeclare()) {
                    rabbitAdmin.declareQueue(this.createQueue(queueName));
                }

                SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
                container.setConnectionFactory(connectionFactory);
                container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
                container.setChannelTransacted(false);
                container.setQueueNames(new String[]{queueName});
                if (this.concurrentConsumers > 0) {
                    container.setConcurrentConsumers(this.concurrentConsumers);
                }

                if (maxConcurrentConsumer != null) {
                    if (maxConcurrentConsumer < 1) {
                        container.setMaxConcurrentConsumers(1);
                    } else {
                        container.setMaxConcurrentConsumers(maxConcurrentConsumer);
                    }
                }

                container.setMessageListener(new ReceiveConfirmListener(messageConsumer, rabbitTemplate, this.maxRetries, this.serviceNotFoundSleep, this.serviceTimeoutSleep));
                container.start();
                LOGGER.debug("register successful, info: serverName:" + serverName + ", queueName:" + queueName + ", path:" + path);
            } else {
                throw new ApplicationException(new ApplicationResponseCode(-1, "mqError", "找不到 " + host + " 配置"));
            }
        }
    }

    private Queue createQueue(String queue) {
        Map<String, Object> param = new HashMap();
        param.put("x-dead-letter-exchange", "lonjoy.dlq.exchange");
        param.put("x-dead-letter-routing-key", "lonjoy.dlq");
        param.put("x-max-priority", 100);
        return new Queue(queue, true, false, false, param);
    }

    private void createDealLetter(RabbitAdmin rabbitAdmin) {
        DirectExchange exchange = new DirectExchange("lonjoy.dlq.exchange");
        Queue queue = new Queue("lonjoy.dlq");
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("lonjoy.dlq");
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
        LOGGER.info(binding.toString());
    }
}
