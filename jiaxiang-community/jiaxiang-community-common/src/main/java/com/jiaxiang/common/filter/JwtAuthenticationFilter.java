package com.jiaxiang.common.filter;

import cn.hutool.json.JSONUtil;
import com.jiaxiang.model.auth.dtos.SecurityUserDTO;
import com.jiaxiang.utils.JwtUtils;
import com.jiaxiang.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.jiaxiang.model.common.constant.AuthConstant.USERID_LOGIN_REDIS_PREFIX;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;

    public JwtAuthenticationFilter(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
            Long userId = JwtUtils.getUserIdFromToken(token);
            String redisKey = USERID_LOGIN_REDIS_PREFIX + userId;
            String loginUserStr = redisUtils.get(redisKey).toString();
            SecurityUserDTO userDTO = JSONUtil.parseObj(loginUserStr).toBean(SecurityUserDTO.class);

            if (userDTO != null) {
                List<SimpleGrantedAuthority> authorities = userDTO.getAuthorities().stream()
                        .map(grantedAuthority -> new SimpleGrantedAuthority(grantedAuthority.getAuthority()))
                        .collect(Collectors.toList());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDTO, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
