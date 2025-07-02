package com.jiaxiang.common.exception;

import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ConditionalOnClass(name = "org.springframework.security.access.AccessDeniedException")
public class SecurityExceptionCatch {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult noPermission(Exception e) {
        return ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }
}