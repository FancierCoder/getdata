package com.zettayun.api.requestParamEntity;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class RequestValueSetByTime {
    private String token;

    private Integer setType;

    private Long startTime;

    private Long endTime;

    private Integer startRow = 0;

    private Integer pageSize = 5;

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

    public Date getStartTime() {
        return new Date(startTime);
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return new Date(endTime);
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
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
