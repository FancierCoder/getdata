package com.zettayun.api.responseParamEntity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel(value = "返回数据")
public class ResponseRow implements Serializable{
	
	@ApiModelProperty("地区名")
    private String areaName;

	@ApiModelProperty("报表数据集合[{“areaName”:”xxx”,”keyword1”:”xxx”},···]")
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
