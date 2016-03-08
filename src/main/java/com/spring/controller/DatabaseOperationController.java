package com.spring.controller;

import com.spring.model.Database;
import com.spring.model.Patch;
import com.spring.service.IDatabaseService;
import com.spring.service.IPatchService;
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
 * Created by YanJun on 2016/2/1.
 */
@Controller
public class DatabaseOperationController {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperationController.class);
    @Autowired
    private IDatabaseService databaseService;



    @RequestMapping(value="/database",method = RequestMethod.GET)
    public String database(){
        logger.debug("user database!");
        return "database";
    }

    @RequestMapping(value = "/getDatabaseList",method = RequestMethod.POST)
    @ResponseBody
    public List<Database> getDatabaseList(){
        List<Database> databaseList = databaseService.findDatabaseList();
        return databaseList;
    }
    @RequestMapping(value = "/doSaveDatabase" , method = RequestMethod.POST)
    public String addNewDatabase(

            @RequestParam("host") String host,
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("url") String url,
            @RequestParam("service") String service,
            @RequestParam("port") String port
    ){
        Database p = new Database();
        p.setUrl(url);
        p.setUser(user);
        p.setPassword(password);
        p.setPort(port);
        p.setHost(host);
        p.setService(service);
        databaseService.addDatabaseInfo(p);
        return "redirect:database";
    }

    @RequestMapping(value="/removeDatabases",method = RequestMethod.POST)
    public String removeDatabaseInfo(@RequestParam("deleteIds") String ids){
        String[] idArr = ids.split(",");
        if(idArr.length > 0) {
            databaseService.deleteDatabaseInfoById(idArr);
        }
        return "redirect:database";
    }

    @RequestMapping(value="/getDatabaseById",method = RequestMethod.POST)
    @ResponseBody
    public Database getDatabaseInfo(@RequestParam("databaseId") int id){
        Database db = databaseService.loadDatabaseById(id);
        //ftpModel.addAttribute("ftp",ftp);
        return db;
    }

    @RequestMapping(value = "/doUpdateDatabase" , method = RequestMethod.POST)
    public String updateDatabase(
            @RequestParam("id") int id,
            @RequestParam("host") String host,
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("url") String url,
            @RequestParam("service") String service,
            @RequestParam("port") String port
    ){
        Database p = new Database();
        p.setId(id);
        p.setUrl(url);
        p.setUser(user);
        p.setPassword(password);
        p.setPort(port);
        p.setHost(host);
        p.setService(service);
        databaseService.updateDatabase(p);
        return "redirect:database";
    }

}
