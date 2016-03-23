package com.spring.controller;

import com.spring.model.Storage;
import com.spring.model.Weblogic;
import com.spring.service.IStorageService;
import com.spring.service.IWeblogicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by YanJun on 2016/3/22.
 */

@Controller
public class StorageOperationController {

    private static final Logger logger = LoggerFactory.getLogger(StorageOperationController.class);
    @Autowired
    private IStorageService storageService;

    @RequestMapping(value="/storage",method = RequestMethod.GET)
    public String storage(){
        logger.debug("user storage!");
        return "storage";
    }

    @RequestMapping(value = "/getStorageList",method = RequestMethod.POST)
    @ResponseBody
    public List<Storage> getStorageList(){
        List<Storage> storageList = storageService.findStorageList();
        return storageList;
    }
}
