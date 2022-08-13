package com.lonjoy.framework.web;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author luke
 * @date 2022/8/13 15:15
 * @desc mybatis plus 配置
 **/
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {
    /**
     * @return CustomerIdGenerator
     * @author luke
     * @date 20:35 2022/8/13
     * @desc 拦截器注册
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * @return CustomerIdGenerator
     * @author luke
     * @date 20:35 2022/8/13
     * @desc 自定义Id生成器
     */
    @Bean
    public CustomerIdGenerator getCustomerIdGenerator() {
        return new CustomerIdGenerator();
    }
}
