package com.zettayun.api.apiImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zettayun.api.DataSetApi;
import com.zettayun.api.requestParamEntity.RequestCreateCollection;
import com.zettayun.api.requestParamEntity.RequestDataSet;
import com.zettayun.api.requestParamEntity.RequestValue;
import com.zettayun.api.responseParamEntity.ResponseRow;
import com.zettayun.entity.DataSet;
import com.zettayun.entity.RShuLie;
import com.zettayun.entity.ShuLie;
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
            return mongoDbService.findByConditionAndOrderBy(shuLie, startRow, pageSize, sortSets, "shulie");
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
                count = mongoDbService.count(criteria, "shulie");
                mongoDbService.update(criteria, shuLie, "shulie");
                map.put("updated", count);
                if (count == 0) {
                    mongoDbService.insert(shuLie, "shulie");
                    dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                    map.put("inserted", 1L);
                }
            }
            else {
                dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                mongoDbService.insert(shuLie, "shulie");
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
            ArrayList<ShuLie> lies = new ArrayList<>();
            ArrayList<DataSet> sets = new ArrayList<>();
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
                    count = mongoDbService.count(criteria, "shulie");
                    updatedCount += count;
                    mongoDbService.update(criteria, shuLie, "shulie");
                    if (count == 0) {
                        lies.add(shuLie);
                        //mongoDbService.insert(shuLie, "shulie");
                        dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                        ++insertedCount;
                    }
                }
                else {
                    dataSet.setPointNumber(dataSet.getPointNumber() + 1);
                    lies.add(shuLie);
                    //mongoDbService.insert(shuLie, "shulie");
                    ++insertedCount;
                }
                map.put("updated", updatedCount);
                map.put("inserted", insertedCount);
                sets.add(dataSet);
                //dataSetService.updateById(dataSet);
            }
            if (lies.size() > 0)
                mongoDbService.insertAll(lies, "shulie");
            dataSetService.updateBatchById(sets);
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
    public Map<String, String> buildDataSetBatches(List<RequestDataSet> dataSets) {
        Map<String, String> map = new HashMap<>();
        ArrayList<DataSet> sets = new ArrayList<>();
        int countKey = 1;
        for (RequestDataSet dataSet : dataSets){
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            DataSet set = new DataSet();
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
            sets.add(set);
            map.put("dataSetName" + countKey, dataSet.getDataSetName());
            map.put("token" + countKey, token);
            countKey++;
        }
        boolean insertBatch = dataSetService.insertBatch(sets);
        if (insertBatch)
            return map;
        return new HashMap<>();
    }

    @Override
    public List<ResponseRow> getElectProCon(List<String> request, Date dataTime) {
        ArrayList<ResponseRow> rows = new ArrayList<>();
        ShuLie shuLie = new ShuLie();
        shuLie.setDate(dataTime);
        List<ShuLie> shuLieList = mongoDbService.findByCondition(shuLie, "report");
        for (String areaName : request){
            EntityWrapper<DataSet> wrapper = new EntityWrapper<>();
            wrapper.like("set_name", areaName);
            List<DataSet> setList = dataSetService.selectList(wrapper);
            ResponseRow row = new ResponseRow();
            row.setAreaName(areaName);
            int countKey = 1;
            for (DataSet dataSet : setList) {
                String token = dataSet.getToken();
                for (ShuLie lie : shuLieList) {
                    if (token.equals(lie.getToken())) {
                        row.getKeyWord().put("keyWordName" + countKey, dataSet.getSetName());
                        row.getKeyWord().put("keyWordValue" + countKey, lie.getValue());
                        countKey++;
                    }
                }
            }
            rows.add(row);
        }
        return rows;
    }

//    public List<Map<String, Object>> getElectProCon2(List<RequestGetElectProCon> request, Long dataTime) {
//        List<Map<String, Object>> rows = new ArrayList<>();
//        ShuLie shuLie = new ShuLie();
//        shuLie.setDate(new Date(dataTime));
//        List<ShuLie> shuLieList = mongoDbService.findByCondition(shuLie, "shulie");
//        for (RequestGetElectProCon electProCon : request){
//            EntityWrapper<DataSet> wrapper = new EntityWrapper<>();
//            wrapper.like("set_name", electProCon.getAreaNames());
//            List<DataSet> setList = dataSetService.selectList(wrapper);
//            HashMap<String, Object> row = new HashMap<>();
//            row.put("areaName", electProCon.getAreaNames());
//            int countKey = 1;
//            for (DataSet dataSet : setList) {
//                String token = dataSet.getToken();
//                for (ShuLie lie : shuLieList) {
//                    if (token.equals(lie.getToken())) {
//                        row.put("keyWordName" + countKey, dataSet.getSetName());
//                        row.put("keyWordValue" + countKey, lie.getValue());
//                        countKey++;
//                    }
//                }
//            }
//            rows.add(row);
//        }
//        return rows;
//    }


}
