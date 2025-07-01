package com.jiaxiang.auth.controller;

import com.jiaxiang.auth.service.AuthService;
import com.jiaxiang.model.auth.dos.UserDO;
import com.jiaxiang.model.auth.dtos.UserLoginDTO;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jiaxiang.model.common.constant.ApiRouterConstant.AUTH_URL_PREFIX;


@RestController
@RequestMapping(AUTH_URL_PREFIX)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseResult<?>> login(@RequestBody UserLoginDTO userLoginDTO){
        // TODO 前端后端加解密
        String codeUserName = userLoginDTO.getUsername();
        String codePassword = userLoginDTO.getPassword();
        // 解密后放回userDO
        UserDO userDO = new UserDO(codeUserName, codePassword);
        return authService.login(userDO);
    }
}
