package com.spring.dao;

import com.spring.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by YanJun on 2016/2/2.
 */

@Repository
public interface UserDao {
    User findUserByEmail(String email);
}
