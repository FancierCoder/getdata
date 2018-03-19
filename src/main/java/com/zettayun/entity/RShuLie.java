package com.zettayun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("数列信息")
public class RShuLie implements Serializable {

    //private String id;

    @ApiModelProperty("token信息")
    private String token;

    @ApiModelProperty("数据点的值")
    private Double value;

    @ApiModelProperty("数据点的日期")
    private Long date;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShuLie{" +
                ", token='" + token + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
