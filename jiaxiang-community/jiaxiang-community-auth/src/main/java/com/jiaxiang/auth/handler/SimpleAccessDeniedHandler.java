package com.jiaxiang.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiaxiang.model.common.constant.SystemConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

import static com.jiaxiang.model.common.constant.SystemConstant.*;

@Component
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HashMap<String, Object> map = new HashMap<>();
        map.put(CODE, 403);
        map.put(MESSAGE, "无权限访问");
        response.setCharacterEncoding("utf-8");
        //处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //返回给前台
        response.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
