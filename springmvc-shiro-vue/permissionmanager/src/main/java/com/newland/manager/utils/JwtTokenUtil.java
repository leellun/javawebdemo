package com.newland.manager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtTokenUtil {
    private static final long EXPIRE_TIME = 1 * 60 * 60 * 1000;
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET = "jwtsecret";


    public static String createToken(String username) {
        return JWT.create().withClaim("username", username).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)).sign(Algorithm.HMAC256(SECRET));
    }

    public static String getUserNmae(String jwtToken) {
        String username;
        try {
            DecodedJWT jwt = JWT.decode(jwtToken);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static boolean isExpire(String token) {
        try {
            return JWT.decode(token).getExpiresAt().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean veryfy(String token, String username) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withClaim("username", username).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
