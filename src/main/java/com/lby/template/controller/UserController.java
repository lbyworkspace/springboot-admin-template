package com.lby.template.controller;

import com.lby.template.dao.SystemUserDao;
import com.lby.template.entity.SystemUser;
import com.lby.template.enums.RoleEnum;
import com.lby.template.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private SystemUserDao userDao;

    @GetMapping("/test")
    public ResponseEntity<Authentication> login(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/add")
    public ResponseEntity<String> register(){
        userService.register(SystemUser.builder().username("111").password(new BCryptPasswordEncoder().encode("111"))
                .role(RoleEnum.USER).build());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/query/{name}")
    public ResponseEntity<SystemUser> query(@PathVariable String name){
        Optional<SystemUser> admin = userDao.findByUsername(name);
        return ResponseEntity.ok(admin.get());
    }

}
