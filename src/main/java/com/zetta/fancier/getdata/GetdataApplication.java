package com.zetta.fancier.getdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.zetta.fancier.getdata.dao")
@EnableSwagger2
public class GetdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetdataApplication.class, args);
    }
}
