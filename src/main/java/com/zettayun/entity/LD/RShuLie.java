package com.zettayun.entity.LD;


import java.io.Serializable;

public class RShuLie implements Serializable {

    //private String id;

    private String token;

    private Double value;

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
