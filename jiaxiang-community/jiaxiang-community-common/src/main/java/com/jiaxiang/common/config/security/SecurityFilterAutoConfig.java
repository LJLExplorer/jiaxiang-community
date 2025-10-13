package com.jiaxiang.common.config.security;

import com.jiaxiang.common.filter.JwtAuthenticationFilter;
import com.jiaxiang.common.handler.AuthenticationEntryPointImpl;
import com.jiaxiang.common.handler.SimpleAccessDeniedHandler;
import com.jiaxiang.utils.RedisUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


@Configuration
@EnableConfigurationProperties(SecurityWhiteProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityFilterAutoConfig {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    private final SimpleAccessDeniedHandler simpleAccessDeniedHandler;

    private final SecurityWhiteProperties securityWhiteProperties;

    public SecurityFilterAutoConfig(AuthenticationEntryPointImpl authenticationEntryPoint, SimpleAccessDeniedHandler simpleAccessDeniedHandler, SecurityWhiteProperties securityWhiteProperties) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.simpleAccessDeniedHandler = simpleAccessDeniedHandler;
        this.securityWhiteProperties = securityWhiteProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "common.security.jwt-filter-enabled", havingValue = "true")
    public JwtAuthenticationFilter jwtAuthenticationFilter(RedisUtils redisUtils) {
        return new JwtAuthenticationFilter(redisUtils);
    }

    @Bean
    @ConditionalOnProperty(name = "common.security.security-filter-chain-enabled", havingValue = "true")
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> {
//                    // 白名单放行
//                    List<String> permitAll = securityWhiteProperties.getWhitelist();
//                    if (permitAll != null && !permitAll.isEmpty()) {
//                        auth.requestMatchers(permitAll.toArray(new String[0])).permitAll();
//                    }
//                    auth.anyRequest().authenticated();
//                })
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(eh -> eh
//                        .authenticationEntryPoint(authenticationEntryPoint)
//                        .accessDeniedHandler(simpleAccessDeniedHandler));
//        return http.build();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(simpleAccessDeniedHandler));
        return http.build();
    }

}