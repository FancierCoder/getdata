package com.zetta.fancier.getdata.api.apiImpl;

import com.alibaba.fastjson.JSONObject;
import com.zetta.fancier.getdata.api.DataSetApi;
import com.zetta.fancier.getdata.entity.RShuLie;
import com.zetta.fancier.getdata.entity.ShuLie;
import com.zetta.fancier.getdata.service.MongoDbService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DataSetApiImpl implements DataSetApi {

    @Resource
    private MongoDbService mongoDbService;

    @Override
    public List<RShuLie> queryDataSet(String token, Integer setType, Integer startRow, Integer pageSize, JSONObject sortSets) {
        if (setType == 1) {
            ShuLie shuLie = new ShuLie();
            shuLie.setToken(token);
            return mongoDbService.findByConditionAndOrderBy(shuLie, startRow, pageSize, sortSets);
        }
        return null;
    }
}
