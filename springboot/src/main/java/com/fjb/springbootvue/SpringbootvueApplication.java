package com.fjb.springbootvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.fjb.springbootvue.mapper")
public class SpringbootvueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootvueApplication.class, args);
    }



}
