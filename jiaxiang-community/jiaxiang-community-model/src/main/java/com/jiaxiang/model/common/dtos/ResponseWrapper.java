package com.jiaxiang.model.common.dtos;

import com.jiaxiang.model.common.enums.AppHttpCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;


/**
 * 增强型响应包装类，简化ResponseEntity的使用
 * 支持所有预定义的AppHttpCodeEnum错误类型
 */
public class ResponseWrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private ResponseWrapper() {
    }

    /**
     * 创建成功响应(200 OK)
     *
     * @param data 响应数据
     * @return 包装后的响应实体
     */
    public static <T> ResponseEntity<ResponseResult<T>> ok(T data) {
        ResponseResult<T> result = ResponseResult.okResult(data);
        return ResponseEntity.ok(result);
    }

    /**
     * 创建自定义状态码的成功响应，带数据
     *
     * @param code    自定义状态码
     * @param message 响应消息
     * @param data    响应数据
     * @return 包装后的响应实体
     */
    public static <T> ResponseEntity<ResponseResult<T>> success(int code, String message, T data) {
        ResponseResult<T> result = new ResponseResult<>(code, message, data);
        return ResponseEntity.ok(result);
    }

    /**
     * 创建自定义状态码的成功响应，带数据
     *
     * @param data 响应数据
     * @return 包装后的响应实体
     */
    public static <T> ResponseEntity<ResponseResult<?>> success(T data) {
        ResponseResult<T> result = ResponseResult.okResult(data);
        return ResponseEntity.ok(result);
    }

    public static <T> ResponseEntity<ResponseResult<?>> successWithPage(T data, Integer currentPage, Integer pageSize, Integer total, Integer pages) {
        PageData<T> result = new PageData<>(currentPage, pageSize, total, pages, data);
        return ResponseEntity.ok(ResponseResult.okResult(result));
    }

    private static ResponseEntity<ResponseResult<?>> error(HttpStatus httpStatus, int code, String message) {
        ResponseResult<?> result = ResponseResult.errorResult(code, message);
        return ResponseEntity.status(httpStatus).body(result);
    }

    private static ResponseEntity<ResponseResult<?>> error(HttpStatus httpStatus, AppHttpCodeEnum appHttpCodeEnum) {
        ResponseResult<?> result = ResponseResult.errorResult(appHttpCodeEnum);
        return ResponseEntity.status(httpStatus).body(result);
    }

    private static ResponseEntity<ResponseResult<?>> error(HttpStatus httpStatus, AppHttpCodeEnum appHttpCodeEnum, String errorMessage) {
        ResponseResult<?> result = ResponseResult.errorResult(appHttpCodeEnum, errorMessage);
        return ResponseEntity.status(httpStatus).body(result);
    }

    /**
     * 创建BadRequest响应(400)
     *
     * @param code    业务错误码
     * @param message 错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> badRequest(int code, String message) {
        return error(HttpStatus.BAD_REQUEST, code, message);
    }

    /**
     * 创建BadRequest响应(400)，使用预定义错误枚举
     *
     * @param appHttpCodeEnum 预定义错误枚举
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> badRequest(AppHttpCodeEnum appHttpCodeEnum) {
        return error(HttpStatus.BAD_REQUEST, appHttpCodeEnum);
    }

    /**
     * 创建BadRequest响应(400)，使用预定义错误枚举和自定义错误消息
     *
     * @param appHttpCodeEnum 预定义错误枚举
     * @param errorMessage    自定义错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> badRequest(AppHttpCodeEnum appHttpCodeEnum, String errorMessage) {
        return error(HttpStatus.BAD_REQUEST, appHttpCodeEnum, errorMessage);
    }

    /**
     * 创建Unauthorized响应(401)
     *
     * @param code    业务错误码
     * @param message 错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> unauthorized(int code, String message) {
        return error(HttpStatus.UNAUTHORIZED, code, message);
    }

    /**
     * 需要登录(401)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> needLogin() {
        return error(HttpStatus.UNAUTHORIZED, AppHttpCodeEnum.NEED_LOGIN);
    }

    /**
     * 密码错误(401)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> loginPasswordError() {
        return error(HttpStatus.UNAUTHORIZED, AppHttpCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR);
    }

    /**
     * 创建Forbidden响应(403)
     *
     * @param code    业务错误码
     * @param message 错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> forbidden(int code, String message) {
        return error(HttpStatus.FORBIDDEN, code, message);
    }

    /**
     * 无权限操作(403)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> noOperatorAuth() {
        return error(HttpStatus.FORBIDDEN, AppHttpCodeEnum.NO_OPERATOR_AUTH);
    }

    /**
     * 需要管理员权限(403)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> needAdmin() {
        return error(HttpStatus.FORBIDDEN, AppHttpCodeEnum.NEED_ADMIN);
    }

    /**
     * 创建NotFound响应(404)
     *
     * @param code    业务错误码
     * @param message 错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> notFound(int code, String message) {
        return error(HttpStatus.NOT_FOUND, code, message);
    }

    /**
     * 数据不存在(404)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> dataNotExist() {
        return error(HttpStatus.NOT_FOUND, AppHttpCodeEnum.DATA_NOT_EXIST);
    }

    /**
     * 创建InternalServerError响应(500)
     *
     * @param code    业务错误码
     * @param message 错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> serverError(int code, String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, code, message);
    }

    /**
     * 服务器内部错误(500)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> serverError() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, AppHttpCodeEnum.SERVER_ERROR);
    }

    // TOKEN相关错误响应

    /**
     * 无效的TOKEN(401)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> tokenInvalid() {
        return error(HttpStatus.UNAUTHORIZED, AppHttpCodeEnum.TOKEN_INVALID);
    }

    /**
     * TOKEN已过期(401)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> tokenExpire() {
        return error(HttpStatus.UNAUTHORIZED, AppHttpCodeEnum.TOKEN_EXPIRE);
    }

    /**
     * TOKEN是必须的(401)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> tokenRequire() {
        return error(HttpStatus.UNAUTHORIZED, AppHttpCodeEnum.TOKEN_REQUIRE);
    }

    // SIGN验签相关错误响应

    /**
     * 无效的SIGN(400)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> signInvalid() {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.SIGN_INVALID);
    }

    /**
     * SIGN已过期(400)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> signTimeout() {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.SIG_TIMEOUT);
    }

    // 参数错误相关响应

    /**
     * 缺少参数(400)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> paramRequire() {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.PARAM_REQUIRE);
    }

    /**
     * 缺少参数(400)
     *
     * @param errorMessage 自定义错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> paramRequire(String errorMessage) {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.PARAM_REQUIRE, errorMessage);
    }

    /**
     * 无效参数(400)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> paramInvalid() {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.PARAM_INVALID);
    }

    /**
     * 无效参数(400)
     *
     * @param errorMessage 自定义错误消息
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> paramInvalid(String errorMessage) {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.PARAM_INVALID, errorMessage);
    }

    /**
     * 图片格式有误(400)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> paramImageFormatError() {
        return error(HttpStatus.BAD_REQUEST, AppHttpCodeEnum.PARAM_IMAGE_FORMAT_ERROR);
    }

    // 数据错误相关响应

    /**
     * 数据已经存在(409 Conflict)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> dataExist() {
        return error(HttpStatus.CONFLICT, AppHttpCodeEnum.DATA_EXIST);
    }

    /**
     * ApUser数据不存在(404)
     *
     * @return 包装后的响应实体
     */
    public static ResponseEntity<ResponseResult<?>> apUserDataNotExist() {
        return error(HttpStatus.NOT_FOUND, AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
    }
}
