package com.jiaxiang.portal;

import com.jiaxiang.common.config.security.SecurityWhiteProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(SecurityWhiteProperties.class)
//@LoadBalancerClient(value = "jiaxiang-activity")
@EnableFeignClients(basePackages = "com.jiaxiang.apis")
@ComponentScan(basePackages = {"com.jiaxiang.common", "com.jiaxiang.portal", "com.jiaxiang.utils"})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
