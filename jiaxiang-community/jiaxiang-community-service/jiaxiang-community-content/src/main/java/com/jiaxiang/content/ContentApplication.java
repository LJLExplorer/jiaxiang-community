package com.jiaxiang.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableFeignClients(basePackages = "com.jiaxiang.apis")
@MapperScan("com.jiaxiang.content.mapper")
@SpringBootApplication
@EnableMethodSecurity
@ComponentScan(basePackages = {"com.jiaxiang.common", "com.jiaxiang.content", "com.jiaxiang.utils", "com.jiaxiang.file"})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
