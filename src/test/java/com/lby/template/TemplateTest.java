package com.lby.template;

import com.lby.template.entity.User;
import com.lby.template.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TemplateTest {

    @Resource
    private UserService userService;

    @Test
    public void test(){
        userService.register(User.builder().username("1111").password("2222").build());
    }
}
