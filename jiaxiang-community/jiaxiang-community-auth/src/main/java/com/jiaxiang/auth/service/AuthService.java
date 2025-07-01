package com.jiaxiang.auth.service;

import com.jiaxiang.model.auth.dos.UserDO;
import com.jiaxiang.model.common.dtos.ResponseResult;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<ResponseResult<?>> login(UserDO userDO);

}
