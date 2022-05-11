package com.lby.template.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;

public class JwtUtils {

    private final static String SIGN_KEY = "12jhk1j2h321k1";

    public static <T> T getField(String jwt,String key,Class<T> target) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get(key,target);
    }
}
