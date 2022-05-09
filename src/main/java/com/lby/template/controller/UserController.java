package com.lby.template.controller;

import com.lby.template.entity.User;
import com.lby.template.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String login(){
        return "test";
    }

    @GetMapping("/add")
    public ResponseEntity<String> register(){
        userService.register(User.builder().username("123").password("123").build());
        return ResponseEntity.ok("ok");
    }

}
