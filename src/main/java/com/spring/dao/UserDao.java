package com.spring.dao;

import com.spring.model.Ftp;
import com.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YanJun on 2016/2/2.
 */

@Repository
public interface UserDao {
    User findUserByEmail(String email);

    User findUserByEmailAndPwd(String email,String password);




}
