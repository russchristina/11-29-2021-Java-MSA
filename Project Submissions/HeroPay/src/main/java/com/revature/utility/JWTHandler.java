package com.revature.utility;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class JWTHandler {

    private static final byte[] secret = Base64.getDecoder().decode("n8i3SBTZk5LqL4V+pYeXp7lm9eDFPhj9Wy/rZ3CuV0E");

    public static byte[] getSecret() {
        return secret;
    }

    public static String generateManagerJwt() {
        return Jwts.builder()
                .claim("user", true)
                .claim("admin", true)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public static String generateEmployeeJwt() {
        return Jwts.builder()
                .claim("user", true)
                .claim("admin", false)
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public static boolean verifyUser(String authorization) {
        try{
            Jwts.parserBuilder()
                    .require("user", true)
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(authorization);
            return true;
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public static boolean verifyAdmin(String authorization) {
        try{
            Jwts.parserBuilder()
                    .require("user", true)
                    .require("admin", true)
                    .setSigningKey(Keys.hmacShaKeyFor(secret))
                    .build()
                    .parseClaimsJws(authorization);
            return true;
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
