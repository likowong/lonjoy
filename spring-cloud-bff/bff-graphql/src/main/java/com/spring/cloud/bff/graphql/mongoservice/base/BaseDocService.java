package com.spring.cloud.bff.graphql.mongoservice.base;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author luke
 * @date 2021/1/15 0015 11:54
 * @desc
 **/
@Service
@Slf4j
public class BaseDocService<T> {
    /**
     * @date 13:03 2021/1/15
     * @desc 集合名称
     */
    private String COLLECTION_NAME;

    public void setCollectionName(String collectionName) {
        COLLECTION_NAME = collectionName;
    }

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * @param o
     * @param collectionName
     * @return {@link T}
     * @author luke
     * @date 12:00 2021/1/15 0015
     * @desc 插入一条数据
     */
    public T insert(T o, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        T t = mongoTemplate.insert(o, COLLECTION_NAME);
        return t;
    }

    /**
     * @param objects
     * @param collectionName
     * @return Collection<T>
     * @author luke
     * @date 11:59 2021/1/15
     * @desc 批量插入
     */
    public Collection<T> insertMany(List<T> objects, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        Collection<T> tCollection = mongoTemplate.insert(objects, COLLECTION_NAME);
        return tCollection;
    }

    /**
     * 存储【一条】用户信息，如果文档信息已经【存在就执行更新】
     *
     * @return 存储的文档信息
     */
    public T save(T t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        return mongoTemplate.save(t, COLLECTION_NAME);
    }

    /**
     * 查询集合中的【全部】文档数据
     *
     * @return 全部文档列表
     */
    public List<T> findAll(Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        // 执行查询集合中全部文档信息
        List<T> documentList = mongoTemplate.findAll(t, COLLECTION_NAME);
        return documentList;
    }

    /**
     * 根据【文档ID】查询集合中文档数据
     *
     * @return 文档信息
     */
    public T findById(String id, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        // 根据文档ID查询集合中文档数据，并转换为对应 Java 对象
        return mongoTemplate.findById(id, t, COLLECTION_NAME);
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档，只取【第一条】数据
     *
     * @return 符合条件的第一条文档
     */
    public T findOne(Query query, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        return mongoTemplate.findOne(query, t, COLLECTION_NAME);
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档，获取其【文档列表】
     *
     * @return 符合条件的文档列表
     */
    public List<T> findByCondition(Query query, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        return mongoTemplate.find(query, t, COLLECTION_NAME);
    }

    /**
     * 更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】
     *
     * @return 执行更新的结果
     */
    public UpdateResult update(Query query, Update update, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        UpdateResult result = mongoTemplate.upsert(query, update, t, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "总共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return result;
    }

    /**
     * 更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】
     *
     * @return 执行更新的结果
     */
    public UpdateResult updateFirst(Query query, Update update, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        UpdateResult result = mongoTemplate.updateFirst(query, update, t, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "总共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return result;
    }

    /**
     * 更新【匹配查询】到的【文档数据集合】中的【所有数据】
     *
     * @return 执行更新的结果
     */
    public Object updateMany(Query query, Update update, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        // 执行更新
        UpdateResult result = mongoTemplate.updateMulti(query, update, t, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "总共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return result;
    }

    /**
     * 删除集合中【符合条件】的【一个]或[多个】文档
     *
     * @return 删除用户信息的结果
     */
    public Object remove(Query query, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        // 执行删除查找到的匹配的全部文档信息
        DeleteResult result = mongoTemplate.remove(query, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除 " + result.getDeletedCount() + " 条文档信息";
        log.info(resultInfo);
        return resultInfo;
    }

    /**
     * 删除【符合条件】的【单个文档】，并返回删除的文档。
     *
     * @return 删除的用户信息
     */
    public T findAndRemove(Query query, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        T rt = mongoTemplate.findAndRemove(query, t, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + rt;
        log.info(resultInfo);
        return rt;
    }

    /**
     * 删除【符合条件】的【全部文档】，并返回删除的文档。
     *
     * @return 删除的全部用户信息
     */
    public List<T> findAllAndRemove(Query query, Class<T> t, String... collectionName) {
        if (collectionName != null && collectionName.length > 0) {
            COLLECTION_NAME = collectionName[0];
        }
        // 执行删除查找到的匹配的全部文档,并返回删除的全部文档信息
        List<T> resultList = mongoTemplate.findAllAndRemove(query, t, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + resultList;
        log.info(resultInfo);
        return resultList;
    }
}
