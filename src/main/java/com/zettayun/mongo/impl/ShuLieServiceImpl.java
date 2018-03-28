package com.zettayun.mongo.impl;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.RequestValueSetByTime;
import com.zettayun.mongo.MongoDbService;
import com.zettayun.entity.LD.ShuLie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShuLieServiceImpl implements MongoDbService<ShuLie> {

    @Resource
    private ShuLieDbDaoImpl mongoDbDao;

    @Override
    public void insert(ShuLie ShuLie, String collectionName) {
        mongoDbDao.insert(ShuLie, collectionName);
    }

    @Override
    public void save(ShuLie shuLie, String collectionName) {
        
        mongoDbDao.save(shuLie, collectionName);
    }

    @Override
    public void insertAll(List<ShuLie> ShuLies, String collectionName) {
        mongoDbDao.insertAll(ShuLies, collectionName);
    }

    @Override
    public void deleteById(String id, String collectionName) {
        mongoDbDao.deleteById(id, collectionName);
    }

    @Override
    public void delete(ShuLie criteriaShuLie, String collectionName) {
        mongoDbDao.delete(criteriaShuLie, collectionName);
    }

    @Override
    public void deleteAll(String collectionName) {
        mongoDbDao.deleteAll(collectionName);
    }

//    @Override
//    public void updateById(ShuLie ShuLie) {
//        mongoDbDao.updateById(ShuLie, collectionName);
//    }

    @Override
    public void update(ShuLie criteriaShuLie, ShuLie ShuLie, String collectionName) {
        mongoDbDao.update(criteriaShuLie, ShuLie, collectionName);
    }

    @Override
    public ShuLie findById(String id, String collectionName) {
        return mongoDbDao.findById(id, collectionName);
    }

    @Override
    public List<ShuLie> findByCondition(ShuLie criteriaShuLie, String collectionName) {
        return mongoDbDao.findByCondition(criteriaShuLie, collectionName);
    }

    @Override
    public List<ShuLie> findAll(String collectionName) {
        return mongoDbDao.findAll(collectionName);
    }

    @Override
    public List<ShuLie> findByConditionAndOrderBy(ShuLie criteriaShuLie, Integer skip, Integer limit, JSONObject sortSet, String collectionName) {
        List<ShuLie> shuLies = mongoDbDao.findByConditionAndOrderBy(criteriaShuLie, skip, limit, sortSet, collectionName);
        
        return shuLies;
    }
    
    @Override
    public List<ShuLie> findByRequest(RequestValueSetByTime request, String collectionName) {
        return mongoDbDao.findByRequest(request, collectionName);
    }

    @Override
    public ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie, String collectionName) {
        return mongoDbDao.findAndModify(criteriaShuLie, updateShuLie, collectionName);
    }

    @Override
    public ShuLie findAndRemove(ShuLie criteriaShuLie, String collectionName) {
        return mongoDbDao.findAndRemove(criteriaShuLie, collectionName);
    }

    @Override
    public long count(ShuLie criteriaShuLie, String collectionName) {
        return mongoDbDao.count(criteriaShuLie, collectionName);
    }

    @Override
    public boolean isExistCollection(String collectionName) {
        return mongoDbDao.isExistCollection(collectionName);
    }

    @Override
    public boolean createCollection(String collectionName) {
        return mongoDbDao.createCollection(collectionName);
    }

    @Override
    public boolean createIndex(String collectionName, List<String> filedNames) {
        return mongoDbDao.createIndex(collectionName, filedNames);
    }
}
