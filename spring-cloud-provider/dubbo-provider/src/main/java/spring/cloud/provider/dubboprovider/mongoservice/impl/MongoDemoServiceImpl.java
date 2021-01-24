package spring.cloud.provider.dubboprovider.mongoservice.impl;


import com.mongodb.client.result.UpdateResult;
import com.spring.cloud.bff.graphql.mongoservice.MongoDemoService;
import com.spring.cloud.bff.graphql.mongoservice.User;
import com.spring.cloud.bff.graphql.mongoservice.base.BaseDocService;
import com.spring.cloud.bff.graphql.mongoservice.base.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * @author luke
 * @date 2021/1/15 0015 13:56
 * @desc
 **/
@Service
public class MongoDemoServiceImpl extends BaseDocService<User> implements MongoDemoService {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private void setBaseCollectionName(){
        setCollectionName("demo");
    }

    @Override
    public void createCollection(String name) {
        collectionService.createCollection(name);
    }

    @Override
    public User saveUser(User user) {
        return save(user);
    }

    @Override
    public UpdateResult updateUser(User user) {
        // 创建条件对象
        Criteria criteria = Criteria.where("id").is(user.getId());
        // 创建查询对象，然后将条件对象添加到其中
        Update update = new Update().set("age", 33).set("name", "zhangsansan");
        Query query = new Query(criteria);
        return update(query,update,User.class);
    }

    @Override
    public User queryUser(User user) {
        return findById(user.getId(),User.class);
    }

    @Override
    public void delUser(User user) {
        // 设置查询条件参数
        int age = 30;
        String sex = "男";
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(age).and("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        remove(query);
    }
}
