package com.zettayun.api.requestParamEntity;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Collection信息", description = "需要创建的Collection信息")
public class RequestCreateCollection {
	
	@ApiModelProperty(value = "集合名", required = true)
    private String collectionName;

	@ApiModelProperty(value = "数据结构：{“KEY1”:0,”KEY2”:1}，key：索引字段，0正序，1倒序", required = true)
    private JSONObject indexSet;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public JSONObject getIndexSet() {
        return indexSet;
    }

    public void setIndexSet(JSONObject indexSet) {
        this.indexSet = indexSet;
    }
}
