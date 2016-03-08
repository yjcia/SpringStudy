package com.spring.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.spring.model.Ftp;
import com.spring.model.User;
import com.spring.service.IFtpService;
import com.spring.service.IUserService;
import com.spring.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YanJun on 2016/2/1.
 */
@Controller
public class FtpOperationController {

    private static final Logger logger = LoggerFactory.getLogger(FtpOperationController.class);
    @Autowired
    private IFtpService ftpService;



    @RequestMapping(value="/ftp",method = RequestMethod.GET)
    public String ftp(){
        logger.debug("user ftp!");
        return "ftp";
    }

    @RequestMapping(value = "/getFtpList",method = RequestMethod.POST)
    @ResponseBody
    public List<Ftp> getFtpList(){
        List<Ftp> ftpList = ftpService.findFtpList();
        return ftpList;
    }
    @RequestMapping(value = "/doSaveFtp" , method = RequestMethod.POST)
    public String addNewFtp(

            @RequestParam("hostname") String hostname,
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("port") String port,
            @RequestParam("path") String path,
            @RequestParam("remark") String remark
    ){
        Ftp ftp = new Ftp();

        ftp.setPassword(password);
        ftp.setHostName(hostname);
        ftp.setUser(user);
        ftp.setRemark(remark);
        ftp.setPath(path);
        ftp.setPort(port);
        ftpService.addFtpInfo(ftp);
        return "redirect:ftp";
    }

    @RequestMapping(value="/removeFtps",method = RequestMethod.POST)
    public String removeFtpInfo(@RequestParam("deleteIds") String ids){
        String[] idArr = ids.split(",");
        if(idArr.length > 0) {
            ftpService.deleteFtpInfoById(idArr);
        }
        return "redirect:ftp";
    }

    @RequestMapping(value="/getFtpById",method = RequestMethod.POST)
    @ResponseBody
    public Ftp getFtpInfo(@RequestParam("ftpId") int id){
        Ftp ftp = ftpService.loadFtpById(id);
        //ftpModel.addAttribute("ftp",ftp);
        return ftp;
    }

    @RequestMapping(value = "/doUpdateFtp" , method = RequestMethod.POST)
    public String updateFtp(
            @RequestParam("id") int id,
            @RequestParam("hostname") String hostname,
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("port") String port,
            @RequestParam("path") String path,
            @RequestParam("remark") String remark
    ){
        Ftp ftp = new Ftp();
        ftp.setId(id);
        ftp.setPassword(password);
        ftp.setHostName(hostname);
        ftp.setUser(user);
        ftp.setRemark(remark);
        ftp.setPath(path);
        ftp.setPort(port);
        ftpService.updateFtp(ftp);
        return "redirect:ftp";
    }

}
