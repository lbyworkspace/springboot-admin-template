package com.lby.template.config.oauth;

import com.lby.template.dao.SystemUserDao;
import com.lby.template.entity.SystemUser;
import com.lby.template.vo.UserDetailVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SystemUserDao systemUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SystemUser> user = systemUserDao.findByUsername(username);
        user.orElseThrow(()->new RuntimeException("用户不存在"));

        SystemUser res = user.get();
        boolean enabled = res.isNormal(); // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(res.getRole().getName()));

        Map<String, Object> info = new HashMap<>();

        info.put("avatarUrl",res.getAvatarUrl());

        return new UserDetailVo(res.getUsername(), res.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities, res.getId(), res.getRealName(), info);
    }
}
