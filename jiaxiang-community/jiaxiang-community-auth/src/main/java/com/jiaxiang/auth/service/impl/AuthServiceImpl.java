package com.jiaxiang.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.jiaxiang.auth.service.AuthService;
import com.jiaxiang.common.exception.CustomException;
import com.jiaxiang.model.auth.dos.UserDO;
import com.jiaxiang.model.auth.dtos.SecurityUserDTO;
import com.jiaxiang.model.common.constant.SystemConstant;
import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.dtos.ResponseWrapper;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import com.jiaxiang.utils.JwtUtils;
import com.jiaxiang.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.jiaxiang.model.common.constant.AuthConstant.LOGIN_REDIS_EXPIRATION_S;
import static com.jiaxiang.model.common.constant.AuthConstant.USERID_LOGIN_REDIS_PREFIX;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final RedisUtils redisUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
        this.authenticationManager = authenticationManager;
        this.redisUtils = redisUtils;
    }


    @Override
    public ResponseEntity<ResponseResult<?>> login(UserDO userDO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDO.getUserName(), userDO.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityUserDTO securityUserDTO = (SecurityUserDTO) authentication.getPrincipal();
            String userName = securityUserDTO.getUserDO().getUserName();
            log.info("{}登录成功", userName);
            String token = JwtUtils.createJwt(securityUserDTO.getUserDO().getId(),
                    securityUserDTO.getUsername());
            Map<String, Object> map = new HashMap<>();
            map.put(SystemConstant.TOKEN, token);
            String securityUserVoStr = JSONUtil.toJsonStr(securityUserDTO);
            redisUtils.set(USERID_LOGIN_REDIS_PREFIX + securityUserDTO.getUserDO().getId(), securityUserVoStr, LOGIN_REDIS_EXPIRATION_S);
            return ResponseWrapper.success(map);
        } catch (BadCredentialsException badCredentialsException) {
            log.error("登陆失败，用户名或密码错误");
            throw new CustomException(AppHttpCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR, "用户名或密码错误");
        }

    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "123456";
        // 加密
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("加密后的密码：" + encodedPassword);

        // 校验
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("密码校验结果：" + matches);
    }

}
