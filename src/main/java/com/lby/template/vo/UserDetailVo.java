package com.lby.template.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserDetailVo extends User {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 额外信息
     */
    private Map<String, Object> additionalInformation;


    public UserDetailVo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String nickname, Map<String, Object> additionalInformation) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.nickname = nickname;
        this.additionalInformation = additionalInformation;
    }
}
