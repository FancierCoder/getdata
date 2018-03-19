package com.zettayun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zettayun.dao")
//@EnableSwagger2
public class GetdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetdataApplication.class, args);
    }
}
