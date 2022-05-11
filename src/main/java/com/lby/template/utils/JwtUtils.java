package com.lby.template.utils;

import com.lby.template.vo.UserInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
public class JwtUtils {

    private final static String SIGN_KEY = "12jhk1j2h321k1";

    public static UserInfoVo parser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();

        Integer id = claims.get("id", Integer.class);
        String nickName = claims.get("nickName", String.class);
        String avatarUrl = claims.get("avatarUrl", String.class);
        List roles = claims.get("authorities", List.class);

        return UserInfoVo.builder().id(id).nickName(nickName).roles(roles).avatarUrl(avatarUrl).build();
    }
}
