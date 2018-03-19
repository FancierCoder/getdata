package com.zettayun.swagger;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.Collection;
import java.util.List;

public abstract class Swagger2Template {

    @Bean
    public Docket configure(TypeResolver typeResolver) {
        SwaggerApiInfo info = apiInfo();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(StringUtils.isEmpty(info.getBasePackage())? RequestHandlerSelectors.any() : RequestHandlerSelectors.basePackage(info.getBasePackage()))//api接口包扫描路径
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();

        docket.pathMapping("/")
                .apiInfo(_apiInfo(info))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(Collection.class, WildcardType.class),
                                typeResolver.resolve(List.class, WildcardType.class))
                )
                .enableUrlTemplating(true)
                .forCodeGeneration(false);

        preConfigure(docket);

        return docket;
    }

    protected abstract void preConfigure(Docket docket);

    protected abstract SwaggerApiInfo apiInfo();

    private ApiInfo _apiInfo(SwaggerApiInfo info) {
        return new ApiInfoBuilder()
                .title(info.getTitle())//设置文档的标题
                .description(info.getDescription())//设置文档的描述
                .termsOfServiceUrl(info.getServiceUrl())//设置文档的License信息
                .contact(new Contact("泽它", "www.zetta.com", null))//设置文档的联系方式
                .version(info.getVersion())//设置文档的版本信息
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",           // url
                "list",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,
                null);
    }
}

