package com.jiaxiang.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.jiaxiang.model.common.constant.SystemConstant.CODE;
import static com.jiaxiang.model.common.constant.SystemConstant.MESSAGE;

@Component
@ConditionalOnClass(AccessDeniedHandler.class)
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ResponseResult<?> result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH, "无权限访问");
        String json = ResponseResult.toJson(result);
        response.getWriter().write(json);

//        HashMap<String, Object> map = new HashMap<>();
//        map.put(CODE, 403);
//        map.put(MESSAGE, "无权限访问");
//        response.setCharacterEncoding("utf-8");
//        //处理编码方式，防止中文乱码的情况
//        response.setContentType("application/json;charset=utf-8");
//        //返回给前台
//        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
