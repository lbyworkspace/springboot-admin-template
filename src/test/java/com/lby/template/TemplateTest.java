package com.lby.template;

import com.lby.template.dao.RoleDao;
import com.lby.template.dao.UserDao;
import com.lby.template.entity.Role;
import com.lby.template.entity.User;
import com.lby.template.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TemplateTest {

    @Resource
    private UserService userService;

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    @Test
    public void test(){
        userService.register(User.builder().username("1111").password("2222").build());
    }

    @Test
    public void test1(){
        Optional<User> admin = userDao.findByUsername("admin");
        System.out.println(admin.get());
    }
}
