package com.spring.tech.test;

import com.spring.service.IStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by YanJun on 2016/4/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:conf/applicationContext.xml",
                "classpath:conf/mybatis-configuration.xml"})
public class BeanUtilTest {

    @Resource
    private IStorageService storageService;

    @Test
    public void testGetBean(){
        System.out.println(storageService);
    }
}
