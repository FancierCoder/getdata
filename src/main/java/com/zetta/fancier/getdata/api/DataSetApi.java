package com.zetta.fancier.getdata.api;

import com.alibaba.fastjson.JSONObject;
import com.zetta.fancier.getdata.entity.RShuLie;

import java.util.List;

public interface DataSetApi {

    List<RShuLie> queryDataSet(String token, Integer setType, Integer startRow, Integer pageSize, JSONObject sortSets);

}
