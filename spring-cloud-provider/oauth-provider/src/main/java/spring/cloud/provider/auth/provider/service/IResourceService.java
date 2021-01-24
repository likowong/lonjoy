package spring.cloud.provider.auth.provider.service;


import spring.cloud.provider.auth.provider.entity.Resource;

import java.util.Set;

public interface IResourceService {
    /**
     * 返回所有的资源定义内容，resources表中
     *
     * @return
     */
    Set<Resource> findAll();

    /**
     * 根据角色code查询到角色把对应的资源定义
     *
     * @param roleCodes
     * @return
     */
    Set<Resource> queryByRoleCodes(String[] roleCodes);
}
