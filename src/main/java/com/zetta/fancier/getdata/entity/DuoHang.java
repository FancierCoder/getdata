package com.zetta.fancier.getdata.entity;

import java.io.Serializable;

public class DuoHang implements Serializable {
    private String id;

    private String token;

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
}
