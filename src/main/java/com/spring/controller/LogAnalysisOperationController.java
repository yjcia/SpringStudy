package com.spring.controller;

import com.spring.model.LogModel;
import com.spring.model.Storage;
import com.spring.service.ILogService;
import com.spring.service.IStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YanJun on 2016/3/22.
 */

@Controller
public class LogAnalysisOperationController {

    private static final Logger logger = LoggerFactory.getLogger(LogAnalysisOperationController.class);
    @Autowired
    private ILogService logService;

    @RequestMapping(value="/logAnalysis",method = RequestMethod.GET)
    public String storage(){
        logger.debug("user log analysis!");
        return "logAnalysis";
    }

    @RequestMapping(value = "/getLogSqlExecTimeList",method = RequestMethod.POST)
    @ResponseBody
    public List<LogModel> getLogSqlExecTimeList(@RequestParam("path") String path){
        List<LogModel> logList = logService.findLogList(path);
        return logList;
    }
}
