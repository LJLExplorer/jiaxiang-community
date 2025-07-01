package com.jiaxiang.auth.service.impl;

import com.jiaxiang.auth.service.AuthService;
import com.jiaxiang.model.auth.dos.UserDO;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public ResponseEntity<ResponseResult<?>> login(UserDO userDO) {
        return null;
    }

}
