package com.jiaxiang.gateway.security;

import com.jiaxiang.common.config.security.SecurityFilterAutoConfig;
import com.jiaxiang.gateway.filter.AuthGlobalFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "gateway")
public class GateWayWhiteProperties {

    /**
     * 放行白名单
     */
    private List<String> whitelist;
}