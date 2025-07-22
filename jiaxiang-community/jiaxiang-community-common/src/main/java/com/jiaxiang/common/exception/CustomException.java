package com.jiaxiang.common.exception;

import com.jiaxiang.model.common.enums.AppHttpCodeEnum;

public class CustomException extends RuntimeException {

    private AppHttpCodeEnum appHttpCodeEnum;

    private String errorMessage;

    public CustomException(AppHttpCodeEnum appHttpCodeEnum) {
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public AppHttpCodeEnum getAppHttpCodeEnum() {
        return appHttpCodeEnum;
    }

    public CustomException() {
        this.appHttpCodeEnum = AppHttpCodeEnum.SERVER_ERROR;
        this.errorMessage = null;
    }

    public CustomException(AppHttpCodeEnum appHttpCodeEnum, String errorMessage) {
        this.appHttpCodeEnum = appHttpCodeEnum;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
