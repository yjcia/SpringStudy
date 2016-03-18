package com.spring.dao.test;

import com.spring.model.User;
import com.spring.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by YanJun on 2016/2/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:conf/applicationContext.xml",
        "classpath:conf/mybatis-configuration.xml"})
public class UserDaoTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testFindUserByEmail(){
        String email = "jun.yan@kewill.com";
        User u = userService.findUserByEmail(email);
        System.out.println(u);
        Assert.assertNotNull(u);
    }


}
