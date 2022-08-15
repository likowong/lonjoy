package com.lonjoy.framework.rabbitmq;

import com.lonjoy.framework.rabbitmq.config.CallBackProperty;
import com.lonjoy.framework.rabbitmq.config.MQCenterClientProperty;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luke
 * @date 2022/8/13 15:15
 * @desc mybatis plus 配置
 **/
@Configuration
@EnableConfigurationProperties({MQCenterClientProperty.class, CallBackProperty.class})
public class RabbitMqConfig {

    @Autowired
    private CallBackProperty callBackProperty;


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        CachingConnectionFactory factory = (CachingConnectionFactory) connectionFactory;
        factory.setPublisherConfirms(true);
        factory.setPublisherReturns(true);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(this.jackson2JsonMessageConverter());
        if (null != callBackProperty.getConfirmCallBackListener()) {
            Class<?> aClass = Class.forName(callBackProperty.getConfirmCallBackListener());
            Object confirmCallback = aClass.newInstance();
            rabbitTemplate.setConfirmCallback((RabbitTemplate.ConfirmCallback) confirmCallback);
        }
        if (null != callBackProperty.getReturnCallBackListener()) {
            Class<?> aClass = Class.forName(callBackProperty.getReturnCallBackListener());
            Object returnCallback = aClass.newInstance();
            rabbitTemplate.setReturnCallback((RabbitTemplate.ReturnCallback) returnCallback);
        }
        return rabbitTemplate;
    }

}
