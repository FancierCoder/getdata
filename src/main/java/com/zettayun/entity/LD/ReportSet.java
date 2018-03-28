package com.zettayun.entity.LD;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportSet {

	private String token;
	
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
