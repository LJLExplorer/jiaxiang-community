package com.jiaxiang.common.exception;


import com.jiaxiang.model.common.dtos.ResponseResult;
import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import feign.FeignException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice  //控制器增强类
@AutoConfiguration
public class ExceptionCatch {

    public ExceptionCatch() {
        log.info("ExceptionCatch 已加载到 Spring 容器中");
    }

    @PostConstruct
    public void init() {
        log.info("ExceptionCatch @PostConstruct 初始化完成");
    }

    /**
     * 处理不可控异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exception(Exception e) {
        log.error("ExceptionCatch catch exception:{}", e.getMessage());
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR, e.getMessage());
    }

    /**
     * 处理可控异常  自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseResult exception(CustomException e) {
        log.error("ExceptionCatch catch exception:{}", e.getErrorMessage());
        return ResponseResult.errorResult(e.getAppHttpCodeEnum(), e.getErrorMessage());
    }


    /**
     * 处理可控异常  Feign异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    @ResponseBody
    public ResponseResult exception(FeignException e) {
        // 处理 503 ServiceUnavailable 异常
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVICE_UNAVAILABLE, "远程服务不可用，请稍后重试");

    }

    /**
     * 处理超过最大上传文件大小异常  MaxUploadSizeExceededException异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseResult exception(MaxUploadSizeExceededException e) {
        log.error("catch exception:{}", e);
        return ResponseResult.errorResult(AppHttpCodeEnum.OVER_MAX_UPLOAD_SIZE);
    }
}
