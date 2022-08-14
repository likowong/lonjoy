package com.lonjoy.external.open.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lonjoy.external.external.open.api.params.SendSmsParams;
import com.lonjoy.external.open.provider.model.Account;

import java.util.List;

/**
 *
 */
public interface AccountService extends IService<Account> {
    /**
     * @param id
     * @return List<Account>
     * @author luke
     * @date 18:24 2022/8/13
     * @desc 通过age获取数据
     */
    List<Account> getAccount(Long id);

    /**
     * @param registerAccountParams
     * @return {@link null}
     * @author luke
     * @date 18:45 2022/8/13
     * @desc 注册用户
     */
    void registerAccount(SendSmsParams registerAccountParams);

}
