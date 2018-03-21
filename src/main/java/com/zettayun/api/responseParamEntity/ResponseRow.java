package com.zettayun.api.responseParamEntity;

import java.io.Serializable;

public class ResponseRow implements Serializable{
    private String areaName;

    private String keyWord;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
