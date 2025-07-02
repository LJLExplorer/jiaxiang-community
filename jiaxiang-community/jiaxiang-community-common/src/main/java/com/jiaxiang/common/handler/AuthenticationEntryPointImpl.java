package com.jiaxiang.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.jiaxiang.model.common.constant.SystemConstant.CODE;
import static com.jiaxiang.model.common.constant.SystemConstant.MESSAGE;

@Component
@ConditionalOnClass(AuthenticationEntryPoint.class)
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN, "用户未登录");
        String json = ResponseResult.toJson(result);
        response.getWriter().write(json);
    }
}
