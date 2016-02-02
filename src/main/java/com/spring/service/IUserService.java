package com.spring.service;

import com.spring.model.User;

/**
 * Created by YanJun on 2016/2/2.
 */
public interface IUserService {
    User findUserByEmail(String email);
}
