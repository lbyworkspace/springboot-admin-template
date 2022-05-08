package com.lby.template.config;

import com.lby.template.dao.UserDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.lby.template.entity.User> user = userDao.findByUsername(username);
        com.lby.template.entity.User user1 = user.get();
        UserDetails userDetails = User.withUsername(user1.getUsername())
                .password(new BCryptPasswordEncoder().encode(user1.getPassword())).authorities("admin").build();
        return userDetails;
    }
}
