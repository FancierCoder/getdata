package com.zettayun.api;

import com.alibaba.fastjson.JSONObject;
import com.zettayun.api.requestParamEntity.RequestCreateCollection;
import com.zettayun.api.requestParamEntity.RequestDataSet;
import com.zettayun.api.requestParamEntity.RequestValue;
import com.zettayun.api.requestParamEntity.RequestValueSetByTime;
import com.zettayun.api.responseParamEntity.ResponseRow;
import com.zettayun.entity.RShuLie;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataSetApi {

    List<RShuLie> queryDataSet(String token, Integer setType, Integer startRow, Integer pageSize, JSONObject sortSets);

    List<RShuLie> queryValueSetByTime(RequestValueSetByTime request);

    Map<String, Long> importDataSet(String token, Object value, Date date, Integer isReplace, Integer setType);

    Map<String, Long> importValueSetBatches(List<RequestValue> shuLies, Integer isReplace, Integer setType);

    Map<String, String> createCollection(RequestCreateCollection collection);

    String buildDataSet(RequestDataSet dataSet);

    Map<String, String> buildDataSetBatches(List<RequestDataSet> dataSets);

    List<ResponseRow> getElectProCon(List<String> request, Date dataTime);

//    List<Map<String, Object>> getElectProCon2(List<RequestGetElectProCon> request, Long dataTime);

}
