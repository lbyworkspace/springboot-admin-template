package com.lby.template.controller;

import com.lby.template.dao.SystemUserDao;
import com.lby.template.entity.SystemUser;
import com.lby.template.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public String login(){
        return "test";
    }

    @GetMapping("/add")
    public ResponseEntity<String> register(){
        userService.register(SystemUser.builder().username("123").password("123").build());
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/query/{name}")
    public ResponseEntity<SystemUser> query(@PathVariable String name){
        Optional<SystemUser> admin = userDao.findByUsername(name);
        return ResponseEntity.ok(admin.get());
    }

}
