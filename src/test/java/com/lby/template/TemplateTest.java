package com.lby.template;

import com.lby.template.dao.SystemUserDao;
import com.lby.template.entity.SystemUser;
import com.lby.template.enums.RoleEnum;
import com.lby.template.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TemplateTest {

    @Resource
    private UserService userService;

    @Resource
    private SystemUserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        userService.register(SystemUser.builder().username("admin").password(passwordEncoder.encode("admin")).isNormal(true).role(RoleEnum.ADMIN).build());
    }

    @Test
    public void test1(){
//        Optional<User> admin = userDao.findByUsername("admin");
//        System.out.println(admin.get());
    }
}
