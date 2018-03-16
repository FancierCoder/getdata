package com.zetta.fancier.getdata.dao;

import com.alibaba.fastjson.JSONObject;
import com.zetta.fancier.getdata.entity.ShuLie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MongoDbDaoImpl implements MongoDbDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    //@Transactional
    public void insert(ShuLie ShuLie) {
        mongoTemplate.insert(ShuLie, "shulie");
    }

    @Override
    //@Transactional
    public void insertAll(List<ShuLie> ShuLies) {
        mongoTemplate.insert(ShuLies, "shulie");
    }

    @Override
    public void deleteById(String id) {
        ShuLie demo = this.findById(id);
        mongoTemplate.remove(demo, "shulie");
    }

    @Override
    public void delete(ShuLie criteriaShuLie) {
        mongoTemplate.remove(criteriaShuLie, "shulie");
    }

    @Override
    public void deleteAll() {
        mongoTemplate.dropCollection(ShuLie.class);
    }

//    @Override
//    public void updateById(ShuLie ShuLie) {
//        Criteria criteria = Criteria.where("_id").is(ShuLie.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("token", ShuLie.getToken())
//                .set("value", ShuLie.getValue())
//                .set("date", ShuLie.getDate());
//        mongoTemplate.updateFirst(query, update, ShuLie.getClass(), "shulie");
//    }

    @Override
    public void update(ShuLie criteriaShuLie, ShuLie ShuLie) {
        Query query = getQuery(criteriaShuLie);
        Update update = Update.update("token", ShuLie.getToken())
                .set("value", ShuLie.getValue())
                .set("date", ShuLie.getDate());
        mongoTemplate.updateMulti(query, update, ShuLie.getClass(), "shulie");
    }

    @Override
    public ShuLie findById(String id) {
        return mongoTemplate.findById(id, ShuLie.class, "shulie");
    }

    @Override
    public List<ShuLie> findAll() {
        return mongoTemplate.findAll(ShuLie.class, "shulie");
    }

    @Override
    public List<ShuLie> find(ShuLie criteriaShuLie, int skip, int limit) {
        Query query = getQuery(criteriaShuLie);
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, ShuLie.class, "shulie");
    }

    public List<ShuLie> findByCondition(ShuLie criteriaShuLie) {
        Query query = getQuery(criteriaShuLie);
        return mongoTemplate.find(query, ShuLie.class, "shulie");
    }

    @Override
    public ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie) {
        Query query = getQuery(criteriaShuLie);
        Update update = Update.update("token", updateShuLie.getToken())
                .set("value", updateShuLie.getValue())
                .set("date", updateShuLie.getDate());
        return mongoTemplate.findAndModify(query, update, ShuLie.class, "shulie");
    }

    @Override
    public ShuLie findAndRemove(ShuLie criteriaShuLie) {
        Query query = getQuery(criteriaShuLie);
        return mongoTemplate.findAndRemove(query, ShuLie.class, "shulie");
    }

    @Override
    public long count(ShuLie criteriaShuLie) {
        Query query = getQuery(criteriaShuLie);
        return mongoTemplate.count(query, "shulie");
    }

    @Override
    public List<ShuLie> findByConditionAndOrderBy(ShuLie criteriaShuLie, Integer skip, Integer limit, JSONObject sortSet) {
        Query query = getQuery(criteriaShuLie);
        List<Sort.Order> orders = new ArrayList<>();
        for (Map.Entry<String, Object> entry : sortSet.entrySet()) {
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            Sort.Order order = new Sort.Order(value > 0 ? Sort.Direction.ASC : Sort.Direction.DESC, key);
            orders.add(order);
        }
        query.with(new Sort(orders));
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, ShuLie.class, "shulie");
    }

    private Query getQuery(ShuLie mongoDemo) {
        if (mongoDemo == null)
            mongoDemo = new ShuLie();
        Query query = new Query();
//        if (mongoDemo.getId() != null) {
//            Criteria criteria = Criteria.where("_id").is(mongoDemo.getId());
//            query.addCriteria(criteria);
//        }
        if (mongoDemo.getValue() != null) {
            Criteria criteria = Criteria.where("value").is(mongoDemo.getValue());
            query.addCriteria(criteria);
        }
        if (mongoDemo.getDate() != null) {
            Criteria dataTime = Criteria.where("date").is(mongoDemo.getDate());
            query.addCriteria(dataTime);
        }
        if (mongoDemo.getToken() != null) {
            Criteria source = Criteria.where("token").is(mongoDemo.getToken());
            query.addCriteria(source);
        }


        return query;
    }
}
