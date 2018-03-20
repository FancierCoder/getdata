package com.zettayun.requestParamEntity;

import java.io.Serializable;
import java.util.Date;

public class RequestValue implements Serializable {
    private String token;

    private Long date;

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
