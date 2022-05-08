package com.lby.template.service.Impl;

import com.lby.template.dao.UserDao;
import com.lby.template.entity.User;
import com.lby.template.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public void register(User user) {
        userDao.save(user);
    }


}
