package com.zettayun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.dao.MongoDbDaoImpl;
import com.zettayun.entity.RShuLie;
import com.zettayun.entity.ShuLie;
import com.zettayun.service.MongoDbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("mongoDbService")
@Transactional
public class MongoDbServiceImpl implements MongoDbService {
    @Resource
    private MongoDbDaoImpl mongoDao;


    @Override
    public void insert(ShuLie ShuLie, String collectionName) {
        mongoDao.insert(ShuLie, collectionName);
    }

    @Override
    public void save(ShuLie shuLie, String collectionName) {
        
        mongoDao.save(shuLie, collectionName);
    }

    @Override
    public void insertAll(List<ShuLie> ShuLies, String collectionName) {
        mongoDao.insertAll(ShuLies, collectionName);
    }

    @Override
    public void deleteById(String id, String collectionName) {
        mongoDao.deleteById(id, collectionName);
    }

    @Override
    public void delete(ShuLie criteriaShuLie, String collectionName) {
        mongoDao.delete(criteriaShuLie, collectionName);
    }

    @Override
    public void deleteAll(String collectionName) {
        mongoDao.deleteAll(collectionName);
    }

//    @Override
//    public void updateById(ShuLie ShuLie) {
//        mongoDao.updateById(ShuLie, collectionName);
//    }

    @Override
    public void update(ShuLie criteriaShuLie, ShuLie ShuLie, String collectionName) {
        mongoDao.update(criteriaShuLie, ShuLie, collectionName);
    }

    @Override
    public ShuLie findById(String id, String collectionName) {
        return mongoDao.findById(id, collectionName);
    }

    @Override
    public List<ShuLie> findByCondition(ShuLie criteriaShuLie, String collectionName) {
        return mongoDao.findByCondition(criteriaShuLie, collectionName);
    }

    @Override
    public List<ShuLie> findAll(String collectionName) {
        return mongoDao.findAll(collectionName);
    }

    @Override
    public List<RShuLie> findByConditionAndOrderBy(ShuLie criteriaShuLie, Integer skip, Integer limit, JSONObject sortSet, String collectionName) {
        List<ShuLie> shuLies = mongoDao.findByConditionAndOrderBy(criteriaShuLie, skip, limit, sortSet, collectionName);
        List<RShuLie> rShuLies = new ArrayList<>();
        for (ShuLie shuLie : shuLies){
            RShuLie rShuLie = new RShuLie();
            rShuLie.setDate(shuLie.getDate().getTime());
            rShuLie.setToken(shuLie.getToken());
            rShuLie.setValue(shuLie.getValue());
            rShuLies.add(rShuLie);
        }
        return rShuLies;
    }

    @Override
    public ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie, String collectionName) {
        return mongoDao.findAndModify(criteriaShuLie, updateShuLie, collectionName);
    }

    @Override
    public ShuLie findAndRemove(ShuLie criteriaShuLie, String collectionName) {
        return mongoDao.findAndRemove(criteriaShuLie, collectionName);
    }

    @Override
    public long count(ShuLie criteriaShuLie, String collectionName) {
        return mongoDao.count(criteriaShuLie, collectionName);
    }

    @Override
    public boolean isExistCollection(String collectionName) {
        return mongoDao.isExistCollection(collectionName);
    }

    @Override
    public boolean createCollection(String collectionName) {
        return mongoDao.createCollection(collectionName);
    }

    @Override
    public boolean createIndex(String collectionName, List<String> filedNames) {
        return mongoDao.createIndex(collectionName, filedNames);
    }
}
