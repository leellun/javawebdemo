package com.newland.manager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtTokenUtil {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET = "jwtsecret";


    public static String createToken(Long userId) {
        return JWT.create().withClaim("userId", userId).sign(Algorithm.HMAC256(SECRET));
    }

    public static Long getUserId(String jwtToken) {
        try {
            DecodedJWT jwt = JWT.decode(jwtToken);
            return jwt.getClaim("userId").asLong();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isExpire(String token) {
        try {
            return JWT.decode(token).getExpiresAt().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean veryfy(String token, Long userId) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withClaim("userId", userId).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
