package com.jiaxiang.gateway.filter;

import com.jiaxiang.utils.JwtUtils;
import com.jiaxiang.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.*;
import static com.jiaxiang.model.common.constant.AuthConstant.USERID_LOGIN_REDIS_PREFIX;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final RedisUtils redisUtils;
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();


    // 白名单路径
    private static final List<String> WHITE_LIST = List.of(
            API_URL_PREFIX + AUTH_URL_PREFIX + "/login",
            API_URL_PREFIX + AUTH_URL_PREFIX + "/register",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/list_community_activities",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/community_activity_detail",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/community_profile",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/grid_management",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/list_committees_members",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/personal_info",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/list_serve_people",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/serve_people_info",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/list_matters",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/community_honor",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/proof_documents",
            API_URL_PREFIX + JIA_HE_URL_PREFIX + "/proof_info"
    );


    public AuthGlobalFilter(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        // 白名单直接放行
//        if (WHITE_LIST.contains(path)) {
//            return chain.filter(exchange);
//        }
        boolean isWhite = WHITE_LIST.stream().anyMatch(white -> pathMatcher.match(white, path));

        if (isWhite) {
            return chain.filter(exchange);
        }

        // 获取Authorization头
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            String body = "{\"code\":401,\"msg\":\"未登录或token无效\"}";
            DataBuffer buffer = exchange.getResponse()
                    .bufferFactory()
                    .wrap(body.getBytes(StandardCharsets.UTF_8));
//            return exchange.getResponse().setComplete();
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

        // 校验Token  取出Bearer
        boolean valid = JwtUtils.verifyToken(token.substring(7));
        if (!valid) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
            String body = "{\"code\":401,\"msg\":\"未登录或token无效\"}";
            DataBuffer buffer = exchange.getResponse()
                    .bufferFactory()
                    .wrap(body.getBytes(StandardCharsets.UTF_8));
//            return exchange.getResponse().setComplete();
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

        Integer userIdFromToken = JwtUtils.getUserIdFromToken(token.substring(7));
        String redisKey = USERID_LOGIN_REDIS_PREFIX + userIdFromToken;
        // TODO redisUtils待实现

        // 校验通过，继续
        return chain.filter(exchange);


    }

    @Override
    public int getOrder() {
        return -1;
    }
}