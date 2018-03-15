package com.zetta.fancier.getdata.service.impl;

import com.zetta.fancier.getdata.dao.MongoDbDaoImpl;
import com.zetta.fancier.getdata.entity.ShuLie;
import com.zetta.fancier.getdata.service.MongoDbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("mongoDbService")
@Transactional
public class MongoDbServiceImpl implements MongoDbService {
    @Resource
    private MongoDbDaoImpl mongoDao;


    @Override
    public void insert(ShuLie ShuLie) {
        mongoDao.insert(ShuLie);
    }

    @Override
    public void insertAll(List<ShuLie> ShuLies) {
        mongoDao.insertAll(ShuLies);
    }

    @Override
    public void deleteById(String id) {
        mongoDao.deleteById(id);
    }

    @Override
    public void delete(ShuLie criteriaShuLie) {
        mongoDao.delete(criteriaShuLie);
    }

    @Override
    public void deleteAll() {
        mongoDao.deleteAll();
    }

    @Override
    public void updateById(ShuLie ShuLie) {
        mongoDao.updateById(ShuLie);
    }

    @Override
    public void update(ShuLie criteriaShuLie, ShuLie ShuLie) {
        mongoDao.update(criteriaShuLie, ShuLie);
    }

    @Override
    public ShuLie findById(String id) {
        return mongoDao.findById(id);
    }

    @Override
    public List<ShuLie> findAll() {
        return mongoDao.findAll();
    }

    @Override
    public List<ShuLie> find(ShuLie criteriaShuLie, int skip, int limit) {
        return mongoDao.find(criteriaShuLie, skip, limit);
    }

    @Override
    public ShuLie findAndModify(ShuLie criteriaShuLie, ShuLie updateShuLie) {
        return mongoDao.findAndModify(criteriaShuLie, updateShuLie);
    }

    @Override
    public ShuLie findAndRemove(ShuLie criteriaShuLie) {
        return mongoDao.findAndRemove(criteriaShuLie);
    }

    @Override
    public long count(ShuLie criteriaShuLie) {
        return mongoDao.count(criteriaShuLie);
    }
}
