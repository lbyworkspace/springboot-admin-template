package com.lby.template.service.Impl;

import com.lby.template.dao.SystemUserDao;
import com.lby.template.entity.SystemUser;
import com.lby.template.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SystemUserDao userDao;

    @Override
    public void register(SystemUser user) {
        userDao.save(user);
    }


}
