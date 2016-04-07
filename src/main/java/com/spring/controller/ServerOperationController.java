package com.spring.controller;

import com.spring.service.IServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YanJun on 2016/4/6.
 */

@Controller
public class ServerOperationController {
    private static final Logger logger = LoggerFactory.getLogger(ServerOperationController.class);
    @Autowired
    private IServerService serverService;
    @RequestMapping(value="/server",method = RequestMethod.GET)
    public String storage(){
        logger.debug("user server!");
        return "server";
    }

    @RequestMapping(value="/getServerHealthInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getServerHealthInfo(){
        return serverService.calServerMemUsage();
    }
}
