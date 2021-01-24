package com.spring.cloud.bff.graphql.mongoservice.base;

import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luke
 * @date 2021/1/15  11:52
 * @desc
 **/
@Service
public class ViewService {
    @Resource
    private MongoTemplate mongoTemplate;
    /**
     * 创建视图
     * 设置视图名
     * 设置获取数据的集合名称
     * 定义视图的管道,可设置视图显示的内容多个筛选条件
     * @return 创建视图结果
     */
    public boolean createView(String newViewName,String collectionName,List<Bson> pipeline) {
        // 执行创建视图
        mongoTemplate.getDb().createView(newViewName, collectionName, pipeline);
        // 检测新的集合是否存在，返回创建结果
        return mongoTemplate.collectionExists(newViewName);
    }

    /**
     * 删除视图
     *
     * @return 删除视图结果
     */
    public boolean dropView(String viewName ) {
        // 设置待删除的视图名称
        // 检测视图是否存在
        if (mongoTemplate.collectionExists(viewName)) {
            // 删除视图
            mongoTemplate.getDb().getCollection(viewName).drop();
            return true;
        }
        // 检测新的集合是否存在，返回创建结果
        return !mongoTemplate.collectionExists(viewName);
    }
}
