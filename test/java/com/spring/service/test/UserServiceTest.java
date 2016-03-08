package com.spring.service.test;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.model.Ftp;
import com.spring.model.User;
import com.spring.service.IFtpService;
import com.spring.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by YanJun on 2016/2/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:conf/applicationContext.xml",
                "classpath:conf/mybatis-configuration.xml"})
public class UserServiceTest {

    @Autowired
    private IFtpService ftpService;

    @Test
    public void testAddFtp(){
        Ftp ftp = new Ftp();
        ftp.setHostName("10.104.46.199");
        ftp.setUser("weblogic");
        ftp.setPassword("als");
        ftp.setPort("22");
        ftp.setPath("/data5/domains");
        ftp.setRemark("Test");
        ftpService.addFtpInfo(ftp);
    }

    @Test
    public void TestGetFtpList(){
        List<Ftp> ftpList = ftpService.findFtpList();
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        String jsonStr = gson.toJson(ftpList);
        System.out.println(jsonStr);
    }
}
