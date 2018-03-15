package com.zetta.fancier.getdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zetta.fancier.getdata.dao")
public class GetdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetdataApplication.class, args);
    }
}
