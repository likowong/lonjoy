package com.springcloud.graphql.mongoservice;

import com.mongodb.client.result.UpdateResult;

/**
 * @author luke
 * @date 2021/1/15 0015 11:38
 * @desc
 **/
public interface MongoDemoService {
    /**
     * @param name
     * @return void
     * @author luke
     * @date 13:54 2021/1/15
     * @desc 新增集合
     */
    void createCollection(String name);
    /**
     * @param user
     * @return User
     * @author luke
     * @date 13:54 2021/1/15
     * @desc 新增
     */
    User saveUser(User user);

    /**
     * @param user
     * @return User
     * @author luke
     * @date 13:54 2021/1/15
     * @desc 更新
     */
    UpdateResult updateUser(User user);

    /**
     * @param user
     * @return User
     * @author luke
     * @date 13:54 2021/1/15
     * @desc 查询
     */
    User queryUser(User user);

    /**
     * @param user
     * @return User
     * @author luke
     * @date 13:54 2021/1/15
     * @desc 删除
     */
    void delUser(User user);
}
