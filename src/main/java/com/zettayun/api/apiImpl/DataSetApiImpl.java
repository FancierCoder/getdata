package com.zettayun.api.apiImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zettayun.api.DataSetApi;
import com.zettayun.api.requestParamEntity.RequestGetElectProCon;
import com.zettayun.api.responseParamEntity.ResponseRow;
import com.zettayun.entity.DataSet;
import com.zettayun.entity.RShuLie;
import com.zettayun.entity.ShuLie;
import com.zettayun.api.requestParamEntity.RequestCreateCollection;
import com.zettayun.api.requestParamEntity.RequestDataSet;
import com.zettayun.api.requestParamEntity.RequestValue;
import com.zettayun.service.DataSetService;
import com.zettayun.service.MongoDbService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class DataSetApiImpl implements DataSetApi {

    @Resource
    private MongoDbService mongoDbService;

    @Resource
    private DataSetService dataSetService;

    @Override
    public List<RShuLie> queryDataSet(String token, Integer setType, Integer startRow, Integer pageSize, JSONObject sortSets) {
        if (setType == 1) {
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(token);
            return mongoDbService.findByConditionAndOrderBy(shuLie, startRow, pageSize, sortSets);
        }
        return null;
    }

    @Override
    public Map<String, Long> importDataSet(String token, Object value, Date date, Integer isReplace, Integer setType) {
        HashMap<String, Long> map = new HashMap<String, Long>();
        if (setType == 1){
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(token);
            shuLie.setValue(Double.valueOf(value.toString()));
            shuLie.setDate(date);
            long count;
            DataSet dataSet = dataSetService.selectByToken(token);
            if (isReplace == 1) {
                ShuLie criteria = new ShuLie();
                criteria.setToken(token);
                criteria.setDate(shuLie.getDate());
                count = mongoDbService.count(criteria);
                mongoDbService.update(criteria, shuLie);
                map.put("updated", count);
                if (count == 0) {
                    mongoDbService.insert(shuLie);
                    dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                    map.put("inserted", 1L);
                }
            }
            else {
                dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                mongoDbService.insert(shuLie);
                map.put("inserted", 1L);
            }
            dataSetService.insert(dataSet);
        }
        return map;
    }

    @Override
    public Map<String, Long> importValueSetBatches(List<RequestValue> shuLies, Integer isReplace, Integer setType) {
        Map<String, Long> map = new HashMap<>();
        if (setType == 1){
            long updatedCount = 0;
            long insertedCount = 0;
            for (RequestValue requestValueSet : shuLies){
                ShuLie shuLie = new ShuLie();
                shuLie.setToken(requestValueSet.getToken());
                shuLie.setDate(requestValueSet.getDate());
                shuLie.setValue(Double.valueOf(requestValueSet.getValue().toString()));
                DataSet dataSet = dataSetService.selectByToken(shuLie.getToken());
                if (isReplace == 1) {
                    long count;
                    ShuLie criteria = new ShuLie();
                    criteria.setToken(shuLie.getToken());
                    criteria.setDate(shuLie.getDate());
                    count = mongoDbService.count(criteria);
                    updatedCount += count;
                    mongoDbService.update(criteria, shuLie);
                    if (count == 0) {
                        mongoDbService.insert(shuLie);
                        dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                        ++insertedCount;
                    }
                }
                else {
                    dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                    mongoDbService.insert(shuLie);
                    ++insertedCount;
                }
                map.put("updated", updatedCount);
                map.put("inserted", insertedCount);
                dataSetService.updateById(dataSet);
            }
            return map;
        }
        return null;
    }

    @Override
    public Map<String, String> createCollection(RequestCreateCollection collection) {
        HashMap<String, String> map = new HashMap<>();
        map.put("result", "success");
        boolean flag = mongoDbService.isExistCollection(collection.getCollectionName());
        if (!flag){
            flag = mongoDbService.createCollection(collection.getCollectionName());
            if (flag) {
                JSONObject indexSet = collection.getIndexSet();
                ArrayList<String> filedName = new ArrayList<>();
                for (Map.Entry<String, Object> entry : indexSet.entrySet()) {
                    String key = entry.getKey();
                    filedName.add(key);
                }
                flag = mongoDbService.createIndex(collection.getCollectionName(), filedName);
                if (!flag)
                    map.put("result", "创建Index失败");
            }else
                map.put("result", "创建Collection失败");
        }else {
            map.put("result", "Collection已存在");
        }
        return map;
    }

    @Override
    public String buildDataSet(RequestDataSet dataSet) {
        DataSet set = new DataSet();
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        set.setToken(token);
        set.setSetName(dataSet.getDataSetName());
        set.setDataSource(dataSet.getDataSource());
        set.setPeriod(dataSet.getPeriod());
        set.setValueUnit(dataSet.getValueUnit());
        set.setSetType(dataSet.getSetType());
        set.setPointNumber(0);
        set.setCreateTime(new Date());
        set.setLastInsertTime(new Date());
        set.setLastUpdateTime(new Date());
        set.setStatus(1);
        boolean insert = dataSetService.insert(set);
        if (insert) {
            return token;
        }
        return null;
    }

    @Override
    public List<ResponseRow> getElectProCon(List<RequestGetElectProCon> request) {
        ArrayList<ResponseRow> rows = new ArrayList<>();
        for (RequestGetElectProCon electProCon : request){
            EntityWrapper<DataSet> wrapper = new EntityWrapper<>();
            wrapper.like("set_name", electProCon.getAreaNames());
            DataSet dataSet = dataSetService.selectOne(wrapper);
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(dataSet.getToken());
        }
        return rows;
    }


}
