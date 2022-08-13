package com.lonjoy.framework.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luke
 * @date 2022/8/13 15:15
 * @desc mybatis plus 配置
 **/
@Configuration
public class WebConfig {
    /**
     * 全局异常拦截器注册
     */
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

}
