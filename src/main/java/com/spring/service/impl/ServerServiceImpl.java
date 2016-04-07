package com.spring.service.impl;

import com.spring.dao.ServerDao;
import com.spring.model.Server;
import com.spring.service.IServerService;
import com.spring.util.EchartsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YanJun on 2016/4/6.
 */

@Service
public class ServerServiceImpl implements IServerService {

    @Autowired
    private ServerDao serverDao;

    public String testDataFromUtil() {
        return "";
    }

    public String calServerMemUsage() {
        List<Server> serverList = serverDao.getCurrentServerMemUsage();
        String memUsageStr = EchartsUtil.generateMemUseageLine(serverList);
        return memUsageStr;
    }

    public String calServerCpuUsage() {
        List<Server> serverList = serverDao.getCurrentServerCpuUsage();
        String cpuUsageStr = EchartsUtil.generateCpuUseageLine(serverList);
        return cpuUsageStr;
    }
}
