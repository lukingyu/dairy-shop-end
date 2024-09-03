package com.haut.ds.utils;

import io.jsonwebtoken.*;

import java.sql.Date;
import java.util.UUID;

public class JwtUtil {
    //用于解密jwt字符串的
    private static final String secretKey = "eyJ0eXAiOiJKV1QiLCGhbGciOiJIUzI1NiJ9";
    //jwt字符串失效时间
    private static final long time = 1000 * 60 * 60 * 24 * 7;

    private JwtUtil(){}

    public static String generateJwtStr(String username){

        JwtBuilder builder = Jwts.builder();
        String jwtStr = builder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                .claim("username", username)
                .setSubject("login-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //签名signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                //将三部分拼接，生成一个jwt字符串
                .compact();

        return jwtStr;
    }

    public static String parseAndGetUsername(String jwtStr){
        JwtParser parser = Jwts.parser().setSigningKey(secretKey);
        Jws<Claims> claimsJws = parser.parseClaimsJws(jwtStr);
        Claims claims = claimsJws.getBody();
        String username = (String)claims.get("username");
        return username;
    }

    public static boolean checkToken(String jwtStr){
        JwtParser parser = Jwts.parser().setSigningKey(secretKey);
        Jws<Claims> claimsJws = parser.parseClaimsJws(jwtStr);
        Claims claims = claimsJws.getBody();
        String username = (String)claims.get("username");
        if (username != null){
            return true;
        }
        return false;
    }
}
