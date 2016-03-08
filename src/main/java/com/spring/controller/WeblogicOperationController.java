package com.spring.controller;

import com.spring.model.Weblogic;
import com.spring.service.IWeblogicService;
import com.spring.service.IWeblogicService;
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
public class WeblogicOperationController {

    private static final Logger logger = LoggerFactory.getLogger(WeblogicOperationController.class);
    @Autowired
    private IWeblogicService weblogicService;



    @RequestMapping(value="/weblogic",method = RequestMethod.GET)
    public String Weblogic(){
        logger.debug("user weblogic!");
        return "weblogic";
    }

    @RequestMapping(value = "/getWeblogicList",method = RequestMethod.POST)
    @ResponseBody
    public List<Weblogic> getWeblogicList(){
        List<Weblogic> WeblogicList = weblogicService.findWeblogicList();
        return WeblogicList;
    }
    @RequestMapping(value = "/doSaveWeblogic" , method = RequestMethod.POST)
    public String addNewFtp(

            @RequestParam("name") String name,
            @RequestParam("rspath") String rspath,
            @RequestParam("remark") String remark,
            @RequestParam("path") String path,
            @RequestParam("host") String host
    ){
        Weblogic p = new Weblogic();
        p.setName(name);
        p.setRspath(rspath);
        p.setRemark(remark);
        p.setHost(host);
        p.setPath(path);
        weblogicService.addWeblogicInfo(p);
        return "redirect:weblogic";
    }

    @RequestMapping(value="/removeWeblogics",method = RequestMethod.POST)
    public String removeWeblogicInfo(@RequestParam("deleteIds") String ids){
        String[] idArr = ids.split(",");
        if(idArr.length > 0) {
            weblogicService.deleteWeblogicInfoById(idArr);
        }
        return "redirect:weblogic";
    }

    @RequestMapping(value="/getWeblogicById",method = RequestMethod.POST)
    @ResponseBody
    public Weblogic getWeblogicInfo(@RequestParam("weblogicId") int id){
        Weblogic weblogic = weblogicService.loadWeblogicById(id);
        //ftpModel.addAttribute("ftp",ftp);
        return weblogic;
    }

    @RequestMapping(value = "/doUpdateWeblogic" , method = RequestMethod.POST)
    public String updateWeblogic(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("rspath") String rspath,
            @RequestParam("remark") String remark,
            @RequestParam("path") String path,
            @RequestParam("host") String host
    ){
        Weblogic p = new Weblogic();
        p.setId(id);
        p.setName(name);
        p.setRspath(rspath);
        p.setRemark(remark);
        p.setHost(host);
        p.setPath(path);
        weblogicService.updateWeblogic(p);
        return "redirect:weblogic";
    }

}
