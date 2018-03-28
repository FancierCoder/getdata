package com.zettayun.api.requestParamEntity;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "请求的信息", description = "请求的数据")
public class RequestGetElectProCon {
	
	@ApiModelProperty(value = "数据点的unix时间戳，精确到毫秒", required = true)
    private Long dataTime;

	@ApiModelProperty(value = "地区名参数结构：name1&name2&name3&name4", required = true)
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
