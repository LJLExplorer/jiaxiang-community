package com.jiaxiang.jiahe;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@MapperScan("com.jiaxiang.jiahe.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class CommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
        log.error("???????????????");
    }
}
