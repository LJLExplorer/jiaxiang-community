package com.jiaxiang.auth.config;

import com.jiaxiang.auth.handler.AuthenticationEntryPointImpl;
import com.jiaxiang.auth.handler.SimpleAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.AUTH_URL_PREFIX;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfig {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    private final SimpleAccessDeniedHandler simpleAccessDeniedHandler;

    public AuthSecurityConfig(AuthenticationEntryPointImpl authenticationEntryPoint, SimpleAccessDeniedHandler simpleAccessDeniedHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.simpleAccessDeniedHandler = simpleAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_URL_PREFIX + "/login",
                                AUTH_URL_PREFIX + "/register").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(simpleAccessDeniedHandler));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}