package com.zettayun.mongo.impl;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.RequestValueSetByTime;
import com.zettayun.mongo.AbstractBaseMongoTemplate;
import com.zettayun.mongo.MongoDbDao;
import com.zettayun.entity.LD.ShuLie;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ShuLieDbDaoImpl extends AbstractBaseMongoTemplate implements MongoDbDao<ShuLie> {
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Override
    //@Transactional
    public void insert(ShuLie ShuLie, String collectionName) {
        mongoTemplate.insert(ShuLie, collectionName);
    }

    public void save(ShuLie shuLie, String collectionName){
        mongoTemplate.save(shuLie, collectionName);
    }

    @Override
    //@Transactional
    public void insertAll(List<ShuLie> ShuLies, String collectionName) {
        mongoTemplate.insert(ShuLies, collectionName);
    }

    @Override
    public void deleteById(String id, String collectionName) {
        ShuLie demo = this.findById(id, collectionName);
        mongoTemplate.remove(demo, collectionName);
    }

    @Override
    public void delete(ShuLie criteriaShuLie, String collectionName) {
        mongoTemplate.remove(criteriaShuLie, collectionName);
    }

    @Override
    public void deleteAll(String collectionName) {
        mongoTemplate.dropCollection(collectionName);
    }

//    @Override
//    public void updateById(ShuLie ShuLie) {
//        Criteria criteria = Criteria.where("_id").is(ShuLie.getId());
//        Query query = new Query(criteria);
//        Update update = Update.update("token", ShuLie.getToken())
//                .set("value", ShuLie.getValue())
//                .set("date", ShuLie.getDate());
//        mongoTemplate.updateFirst(query, update, ShuLie.getClass(), collectionName);
//    }

    @Override
    public void update(ShuLie criteriaShuLie, ShuLie ShuLie, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        Update update = Update.update("token", ShuLie.getToken())
                .set("value", ShuLie.getValue())
                .set("date", ShuLie.getDate());
        mongoTemplate.updateMulti(query, update, ShuLie.getClass(), collectionName);
    }

    @Override
    public ShuLie findById(String id, String collectionName) {
        return mongoTemplate.findById(id, ShuLie.class, collectionName);
    }

    @Override
    public List<ShuLie> findAll(String collectionName) {
        return mongoTemplate.findAll(ShuLie.class, collectionName);
    }

    @Override
    public List<ShuLie> find(ShuLie criteriaShuLie, int skip, int limit, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, ShuLie.class, collectionName);
    }

    public List<ShuLie> findByCondition(ShuLie criteriaShuLie, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        return mongoTemplate.find(query, ShuLie.class, collectionName);
    }

    @Override
    public ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        Update update = Update.update("token", updateShuLie.getToken())
                .set("value", updateShuLie.getValue())
                .set("date", updateShuLie.getDate());
        return mongoTemplate.findAndModify(query, update, ShuLie.class, collectionName);
    }

    @Override
    public ShuLie findAndRemove(ShuLie criteriaShuLie, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        return mongoTemplate.findAndRemove(query, ShuLie.class, collectionName);
    }

    @Override
    public long count(ShuLie criteriaShuLie, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        return mongoTemplate.count(query, collectionName);
    }

    
    public List<ShuLie> findByConditionAndOrderBy(ShuLie criteriaShuLie, Integer skip, Integer limit, JSONObject sortSet, String collectionName) {
        Query query = getQuery(criteriaShuLie);
        List<Sort.Order> orders = new ArrayList<>();
        try {
            Assert.notNull(sortSet, "sortSet为空");
        } catch (Exception e) {
            sortSet = new JSONObject();
        }
        for (Map.Entry<String, Object> entry : sortSet.entrySet()) {
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            Sort.Order order = new Sort.Order(value > 0 ? Sort.Direction.ASC : Sort.Direction.DESC, key);
            orders.add(order);
        }
        if (orders.size() > 0)
        	query.with(new Sort(orders));
        query.skip(skip);
        query.limit(limit);
        return mongoTemplate.find(query, ShuLie.class, collectionName);
    }
    
    public List<ShuLie> findByRequest(RequestValueSetByTime request, String collectionName){
        ShuLie shuLie = new ShuLie();
        shuLie.setToken(request.getToken());
        Query query = getQuery(shuLie);
        Criteria criteria = Criteria.where("date").lte(request.getEndTime()).gte(request.getStartTime());
        query.addCriteria(criteria);
        query.limit(request.getPageSize());
        query.skip(request.getStartRow());
        List<Sort.Order> orders = new ArrayList<>();
        JSONObject sortSet = new JSONObject();
        try {
            sortSet = request.getSortSet();
            Assert.notNull(sortSet, "sortSet为空");
        } catch (Exception e) {

        }
        for (Map.Entry<String, Object> entry : sortSet.entrySet()) {
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            Sort.Order order = new Sort.Order(value > 0 ? Sort.Direction.ASC : Sort.Direction.DESC, key);
            orders.add(order);
        }
        if (orders.size() > 0)
            query.with(new Sort(orders));
        return mongoTemplate.find(query, ShuLie.class, collectionName);
    }

    public boolean isExistCollection(String collectionName){
        return mongoTemplate.collectionExists(collectionName);
    }


    public boolean createCollection(String collectionName){
        try {
            mongoTemplate.createCollection(collectionName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean createIndex(String collectionName ,List<String> filedNames){
        try {
            TextIndexDefinition index = TextIndexDefinition.builder().build();
            for (String filedName : filedNames) {
                TextIndexDefinition.TextIndexedFieldSpec fieldSpec = new TextIndexDefinition.TextIndexedFieldSpec(filedName);
                index.addFieldSpec(fieldSpec);
            }
            mongoTemplate.indexOps(collectionName).ensureIndex(index);
            //System.out.println(s);
        } catch (Exception e) {
            return false;
        }
        return true;
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

	@Override
	public void updateById(String id, ShuLie document, String collectionName) {
		Query query = new Query();
		Criteria criteria = Criteria.where("_id").is(id);
		Update update = Update.update("token", document.getToken())
                .set("value", document.getValue())
                .set("date", document.getDate());
		query.addCriteria(criteria);
		mongoTemplate.updateFirst(query, update, collectionName);
		
	}
}
