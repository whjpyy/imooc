package com.example.mybatisanddruid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mybatisanddruid.mapper")
public class MybatisAndDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisAndDruidApplication.class, args);
    }
}
