package com.lonjoy.framework.rabbitmq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author luke
 * @date 2022/8/15 21:16
 * @desc
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MqCenterListenerEndpoint {
    String listenQueue();

    int maxConcurrentConsumers() default 0;

    String host() default "";
}
