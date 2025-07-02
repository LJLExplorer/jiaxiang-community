package com.jiaxiang.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.jiaxiang.apis")
@MapperScan("com.jiaxiang.activity.mapper")
@ComponentScan(basePackages = {"com.jiaxiang.common", "com.jiaxiang.activity", "com.jiaxiang.utils"})
@SpringBootApplication
public class ActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }
}
