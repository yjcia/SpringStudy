package com.spring.dao;

import com.spring.model.User;

/**
 * Created by YanJun on 2016/2/2.
 */
public interface UserDao {
    User findUserByEmail(String email);
}
