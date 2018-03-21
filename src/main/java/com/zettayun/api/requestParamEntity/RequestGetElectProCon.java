package com.zettayun.api.requestParamEntity;

import java.util.Date;

public class RequestGetElectProCon {
    private Long dataTime;

    private String areaNames;

    public Date getDataTime() {
        return new Date(dataTime);
    }

    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }

    public String getAreaNames() {
        return areaNames;
    }

    public void setAreaNames(String areaNames) {
        this.areaNames = areaNames;
    }
}
