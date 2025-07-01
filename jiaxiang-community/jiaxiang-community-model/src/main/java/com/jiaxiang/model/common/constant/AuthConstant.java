package com.jiaxiang.model.common.constant;

/**
 * 权限部分路由
 */
public class AuthConstant {

    /**
     * JWT秘钥
     */
    public static final String SECRET_KEY = "q7H9bK2xYpW6vRmFz3JtLdNc8GuA5XoE";

    /**
     * 登录后存到redis的用户前缀
     */
    public static final String USERID_LOGIN_REDIS_PREFIX = "userid:login:";

    // JWT过期时间1小时
    public static final long JWT_EXPIRATION_MS = 60 * 60 * 1000;

    // Redis存放用户信息过期时间1小时
    public static final long LOGIN_REDIS_EXPIRATION_S = 60 * 60;




}
