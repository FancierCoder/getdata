package com.zettayun.api.responseParamEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResponseRow implements Serializable{
    private String areaName;

    private Map<String, Object> keyWord = new HashMap<>();

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Map<String, Object> getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(Map<String, Object> keyWord) {
        this.keyWord = keyWord;
    }
}
