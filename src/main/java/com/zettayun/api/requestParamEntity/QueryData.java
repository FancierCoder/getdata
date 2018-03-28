package com.zettayun.api.requestParamEntity;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class QueryData {

    private String token;

    private Integer setType;

    private Integer startRow = 0;

    private Integer pageSize = 10;

    private JSONObject sortSet = new JSONObject();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getSetType() {
        return setType;
    }

    public void setSetType(Integer setType) {
        this.setType = setType;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public JSONObject getSortSet() {
        return sortSet;
    }

    public void setSortSet(JSONObject sortSet) {
        this.sortSet = sortSet;
    }
}
