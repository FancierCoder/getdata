package com.zettayun.api.requestParamEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "数据点")
public class Set implements Serializable {
	
	@ApiModelProperty(value = "数据点token", required = true)
    private String token;

	@ApiModelProperty(value = "数据点日期", required = true)
    private Long date;

	@ApiModelProperty(value = "数据点的值", required = true)
    private Object value;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return new Date(this.date);
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
