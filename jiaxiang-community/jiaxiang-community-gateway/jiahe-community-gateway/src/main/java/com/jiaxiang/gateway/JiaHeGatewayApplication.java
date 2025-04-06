package com.jiaxiang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JiaHeGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiaHeGatewayApplication.class, args);
    }
}
