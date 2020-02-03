package com.itworkorder.sqlgen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.itworkorder.sqlgen.dao"})
@ServletComponentScan(basePackages = {"com.itworkorder.sqlgen.listener"})


public class SqlGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlGenApplication.class, args);
    }

}
