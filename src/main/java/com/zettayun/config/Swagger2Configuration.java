package com.zettayun.config;


import com.zettayun.GetdataApplication;
import com.zettayun.common.StatusCode;
import com.zettayun.swagger.Swagger2Template;
import com.zettayun.swagger.SwaggerApiInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Configuration extends Swagger2Template {

    @Value("${spring.application.name}")
    private String appName;

    public Swagger2Configuration() {
        System.out.println("aaaaaaaaaa"+GetdataApplication.class.getPackage().getName());
    }
    protected SwaggerApiInfo apiInfo() {
        SwaggerApiInfo info = new SwaggerApiInfo();
        return info.title(appName + " RESTful APIs")//设置文档的标题
                .description("更多Swagger相关文章请关注：https://swagger.io/docs/specification/about/")//设置文档的描述
                .version("1.0")//设置文档的版本信息
                .basePackage("com.zetta.fancier.getdata.*");
    }


    @Override
    protected void preConfigure(Docket docket) {
        docket.useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, extractStatusCodes());
    }

    private List<ResponseMessage> extractStatusCodes() {
        final LinkedList<ResponseMessage> list = new LinkedList<>();
        for (StatusCode statusCodes : StatusCode.values()) {
            final ResponseMessageBuilder builder = new ResponseMessageBuilder();
            final ResponseMessage message = builder
                    .code(statusCodes.code())
                    .message(statusCodes.message())
                    .build();
            list.add(message);
        }
        return list;
    }
}

