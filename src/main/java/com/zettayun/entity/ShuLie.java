package com.zettayun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@ApiModel("数列信息")
@Document
public class ShuLie implements Serializable {

    //private String id;

    @ApiModelProperty("token信息")
    private String token;

    @ApiModelProperty("数据点的值")
    private Double value;

    @ApiModelProperty("数据点的日期")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
