package com.spring.service;

import com.spring.model.Ftp;
import com.spring.model.User;

import java.util.List;

/**
 * Created by YanJun on 2016/2/2.
 */
public interface IUserService {
    User findUserByEmail(String email);

    User findUserByEmailAndPwd(String email,String password);

}
