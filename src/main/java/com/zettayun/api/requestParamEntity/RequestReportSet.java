package com.zettayun.api.requestParamEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "报表的信息")
public class RequestReportSet {

	@ApiModelProperty(value = "报表的token", required = true)
	private String token;
	
	@ApiModelProperty(value = "报表的Json值", required = true)
	private String value;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
