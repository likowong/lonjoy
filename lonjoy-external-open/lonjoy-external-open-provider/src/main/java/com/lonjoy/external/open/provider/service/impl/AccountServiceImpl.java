package com.lonjoy.external.open.provider.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lonjoy.external.external.open.api.params.SendSmsParams;
import com.lonjoy.framework.redis.CacheUtil;
import com.lonjoy.external.open.provider.mapper.AccountMapper;
import com.lonjoy.external.open.provider.model.Account;
import com.lonjoy.external.open.provider.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountService {
    /**
     * @param id
     * @return List<Account>
     * @author luke
     * @date 18:24 2022/8/13
     * @desc 通过age获取数据
     */
    @Override
    public List<Account> getAccount(Long id) {
        return getBaseMapper().getAccount(id);
    }

    /**
     * @param registerAccountParams
     * @return {@link null}
     * @author luke
     * @date 18:45 2022/8/13
     * @desc 注册用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerAccount(SendSmsParams registerAccountParams) {
        Account account = BeanUtil.copyProperties(registerAccountParams, Account.class);
        save(account);
        CacheUtil.setString("LonJoy:test", "test", 30);
        String string = CacheUtil.getString("LonJoy:test");
        System.out.println(string);
        throw new RuntimeException("测试事务");
    }
}




