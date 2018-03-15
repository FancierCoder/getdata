package com.zetta.fancier.getdata.entity;

import java.io.Serializable;
import java.util.Date;

public class ShuLie implements Serializable {

    private String id;

    private String token;

    private Double value;

    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
