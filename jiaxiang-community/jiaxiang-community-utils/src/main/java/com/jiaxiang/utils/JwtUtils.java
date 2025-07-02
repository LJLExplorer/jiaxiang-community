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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jiaxiang.model.common.constant.AuthConstant.JWT_EXPIRATION_MS;
import static com.jiaxiang.model.common.constant.AuthConstant.SECRET_KEY;

@Slf4j
public class JwtUtils {

    /**
     * 创建jwt
     *
     * @param id       用户id
     * @param username 用户名
     * @param authList 权限列表
     * @return
     */
    public static String createJwt(Long id, String username, List<String> authList) {
        Map<String, Object> headClaims = new HashMap<>();
        Date now = new Date();
        headClaims.put("alg", "HS256");
        headClaims.put("typ", "JWT");
        //使用HS256进行签名，secret作为密钥
        return JWT.create().withHeader(headClaims)
                .withIssuer(username)
                .withClaim("UserName", username)
                .withClaim("userId", id)
                .withClaim("authList", authList)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + JWT_EXPIRATION_MS))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    /**
     * 创建jwt, 通常不建议在token中存放权限，容易被破解
     *
     * @param id       用户id
     * @param username 用户名
     * @return
     */
    public static String createJwt(Long id, String username) {
        Map<String, Object> headClaims = new HashMap<>();
        Date now = new Date();
        headClaims.put("alg", "HS256");
        headClaims.put("typ", "JWT");
        //使用HS256进行签名，secret作为密钥
        return JWT.create().withHeader(headClaims)
                .withIssuer(username)
                .withClaim("UserName", username)
                .withClaim("userId", id)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + JWT_EXPIRATION_MS))
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

    public static Long getUserIdFromToken(String token) {
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            DecodedJWT verify = build.verify(token);
//            String userName = verify.getClaim("UserName").asString();
            Long userId = verify.getClaim("userId").asLong();
//            List<String> authList = verify.getClaim("authList").asList(String.class);
            return userId;
        } catch (Exception e) {
            log.error("JWT不合法");
//            System.out.println("JWT不合法");
            return -1L;
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
