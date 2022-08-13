package com.lonjoy.auth.provider.mapper;

import com.lonjoy.auth.provider.model.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.lonjoy.auth.provider.model.Account
 */
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * @param id
     * @return List<Account>
     * @author luke
     * @date 18:24 2022/8/13
     * @desc 通过age获取数据
     */
    List<Account> getAccount(@Param(value = "userId") Long id);
}




