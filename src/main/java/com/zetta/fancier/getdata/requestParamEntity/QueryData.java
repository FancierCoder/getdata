package com.zetta.fancier.getdata.requestParamEntity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

@ApiModel(value = "QueryData", description="QueryData描述")
public class QueryData {

    @ApiModelProperty(value = "token信息", required = true)
    private String token;

    @ApiModelProperty(value = "setType信息", required = true, example = "1")
    private Integer setType = 1;

    @ApiModelProperty("从哪一行开始分页")
    private Integer startRow = 0;

    @ApiModelProperty(value = "页面大小", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty("key:value,key：排序关键字，0正序，1倒序")
    private JSONObject sortSet = new JSONObject(new HashMap<>());

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
