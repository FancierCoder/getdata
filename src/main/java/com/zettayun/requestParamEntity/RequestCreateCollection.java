package com.zettayun.requestParamEntity;

import com.alibaba.fastjson.JSONObject;

public class RequestCreateCollection {
    private String collectionName;

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
