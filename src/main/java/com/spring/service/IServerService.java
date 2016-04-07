package com.spring.service;

import com.spring.model.Server;

import java.util.List;

/**
 * Created by YanJun on 2016/4/6.
 */
public interface IServerService {
    String testDataFromUtil();

    String calServerMemUsage();

    String calServerCpuUsage();
}
