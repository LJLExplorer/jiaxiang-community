package com.jiaxiang.gateway;

import com.jiaxiang.gateway.security.GateWayWhiteProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(GateWayWhiteProperties.class)
@ComponentScan(basePackages = {"com.jiaxiang.utils", "com.jiaxiang.gateway", "com.jiaxiang.common"})
public class CommunityGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityGatewayApplication.class, args);
    }
}
