package com.spring.server.test;

import com.spring.dao.ServerDao;
import com.spring.model.Server;
import com.spring.util.EchartsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by YanJun on 2016/4/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
        {"classpath:conf/applicationContext.xml",
                "classpath:conf/mybatis-configuration.xml"})
public class ServerTest {

    @Autowired
    private ServerDao serverDao;

    @Test
    public void testFreeCommand(){
        //serverDao.getCurrentServerMemUsage();
        serverDao.getCurrentServerCpuUsage();
    }

//    @Test
//    public void testGenerateLineJson(){
//        System.out.println(EchartsUtil.generatememUseageLine(new Server()));
//    }

}
