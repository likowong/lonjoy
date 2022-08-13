package com.lonjoy.framework.web;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @author luke
 * @date 2022/8/13 20:32
 * @desc Id自定义
 **/
public class CustomerIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdGenerator.generateId();
    }
}
