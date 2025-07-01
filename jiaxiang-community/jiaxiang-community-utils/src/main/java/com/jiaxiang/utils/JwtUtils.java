package com.jiaxiang.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private static final String SECRET_KEY = "q7H9bK2xYpW6vRmFz3JtLdNc8GuA5XoE";
    // 1小时
    private static final long EXPIRATION_MS = 60 * 60 * 1000;

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 创建jwt
     *
     * @param id       用户id
     * @param username 用户名
     * @param authList 权限列表
     * @return
     */
    public static String createJwt(Integer id, String username, List<String> authList) {
        Map<String, Object> headClaims = new HashMap<>();
        headClaims.put("alg", "HS256");
        headClaims.put("typ", "JWT");
        //使用HS256进行签名，secret作为密钥
        return JWT.create().withHeader(headClaims)
                .withIssuer(username)
                .withClaim("UserName", username)
                .withClaim("userId", id)
                .withClaim("authList", authList)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public static boolean verifyToken(String token) {
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            build.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            log.error("JWT已过期");
        } catch (SignatureVerificationException e) {
            log.error("JWT签名不正确");
        } catch (JWTDecodeException e) {
            log.error("JWT格式不正确");
        } catch (Exception e) {
            log.error("JWT不合法");
        }
        return false;
    }

    public static Integer getUserIdFromToken(String token) {
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT verify = build.verify(token);
//            String userName = verify.getClaim("UserName").asString();
            Integer userId = verify.getClaim("userId").asInt();
//            List<String> authList = verify.getClaim("authList").asList(String.class);
            return userId;
        } catch (Exception e) {
            log.error("JWT不合法");
//            System.out.println("JWT不合法");
            return -1;
        }
    }

    public static String getUserNameFromToken(String token) {
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT verify = build.verify(token);
            String userName = verify.getClaim("UserName").asString();
//            Integer userId = verify.getClaim("userId").asInt();
//            List<String> authList = verify.getClaim("authList").asList(String.class);
            return userName;
        } catch (Exception e) {
            log.error("JWT不合法");
//            System.out.println("JWT不合法");
            return "";
        }
    }

    public static List<String> getAuthListFromToken(String token) {
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT verify = build.verify(token);
//            String userName = verify.getClaim("UserName").asString();
//            Integer userId = verify.getClaim("userId").asInt();
            List<String> authList = verify.getClaim("authList").asList(String.class);
            return authList;
        } catch (Exception e) {
            log.error("JWT不合法");
            return null;
        }
    }
}
