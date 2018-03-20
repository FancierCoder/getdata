package com.zettayun.api;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.entity.RShuLie;
import com.zettayun.requestParamEntity.RequestCreateCollection;
import com.zettayun.requestParamEntity.RequestDataSet;
import com.zettayun.requestParamEntity.RequestValue;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataSetApi {

    List<RShuLie> queryDataSet(String token, Integer setType, Integer startRow, Integer pageSize, JSONObject sortSets);

    Map<String, Long> importDataSet(String token, Object value, Date date, Integer isReplace, Integer setType);

    Map<String, Long> importValueSetBatches(List<RequestValue> shuLies, Integer isReplace, Integer setType);

    Map<String, String> createCollection(RequestCreateCollection collection);

    String buildDataSet(RequestDataSet dataSet);
}
