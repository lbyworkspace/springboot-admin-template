package com.lby.template.config.oauth;

import com.lby.template.vo.UserDetailVo;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Component
@Primary
public class CustomToken implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Object principal = oAuth2Authentication.getPrincipal();
        // 后台用户把部分信息带入token中
        if (principal instanceof UserDetailVo) {
            UserDetailVo user = (UserDetailVo) principal;
            // 附加信息到jwt中，注意不能放置敏感信息
            Map<String, Object> info = new HashMap<>();
            info.put("id", user.getId());
            info.put("nickName", user.getNickname());
            // 如果有额外信息
            if (user.getAdditionalInformation() != null) {
                info.putAll(user.getAdditionalInformation());
            }
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        }
        return oAuth2AccessToken;
    }
}
