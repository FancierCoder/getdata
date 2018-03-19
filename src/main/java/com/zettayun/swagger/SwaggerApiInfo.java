package com.zettayun.swagger;

public class SwaggerApiInfo {
    //设置文档的标题
    private String title = "";
    //设置文档的描述
    private String description = "";
    //设置文档的版本信息
    private String version = "";
    //设置文档的License信息
    private String serviceUrl = "";
    //api接口包扫描路径
    private String basePackage = "";

    public String getTitle() {
        return title;
    }
    public SwaggerApiInfo title(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public SwaggerApiInfo description(String description) {
        this.description = description;
        return this;
    }
    public String getVersion() {
        return version;
    }
    public SwaggerApiInfo version(String version) {
        this.version = version;
        return this;
    }
    public String getServiceUrl() {
        return serviceUrl;
    }
    public SwaggerApiInfo serviceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        return this;
    }
    public String getBasePackage() {
        return basePackage;
    }
    public SwaggerApiInfo basePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }

}

