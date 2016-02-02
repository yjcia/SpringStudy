package com.spring.service.impl;

import com.spring.dao.UserDao;
import com.spring.model.User;
import com.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YanJun on 2016/2/2.
 */

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserDao userDao;

    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
