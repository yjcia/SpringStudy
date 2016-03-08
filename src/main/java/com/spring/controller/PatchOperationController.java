package com.spring.controller;

import com.spring.model.Ftp;
import com.spring.model.Patch;
import com.spring.service.IFtpService;
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
public class PatchOperationController {

    private static final Logger logger = LoggerFactory.getLogger(PatchOperationController.class);
    @Autowired
    private IPatchService patchService;



    @RequestMapping(value="/patch",method = RequestMethod.GET)
    public String patch(){
        logger.debug("user patch!");
        return "patch";
    }

    @RequestMapping(value = "/getPatchList",method = RequestMethod.POST)
    @ResponseBody
    public List<Patch> getPatchList(){
        List<Patch> patchList = patchService.findPatchList();
        return patchList;
    }
    @RequestMapping(value = "/doSavePatch" , method = RequestMethod.POST)
    public String addNewFtp(

            @RequestParam("patchname") String patchname,
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("script") String script,
            @RequestParam("path") String path,
            @RequestParam("host") String host
    ){
        Patch p = new Patch();
        p.setPatch_name(patchname);
        p.setUser(user);
        p.setPassword(password);
        p.setScript(script);
        p.setHost(host);
        p.setPath(path);
        patchService.addPatchInfo(p);
        return "redirect:patch";
    }

    @RequestMapping(value="/removePatchs",method = RequestMethod.POST)
    public String removePatchInfo(@RequestParam("deleteIds") String ids){
        String[] idArr = ids.split(",");
        if(idArr.length > 0) {
            patchService.deletePatchInfoById(idArr);
        }
        return "redirect:patch";
    }

    @RequestMapping(value="/getPatchById",method = RequestMethod.POST)
    @ResponseBody
    public Patch getPatchInfo(@RequestParam("patchId") int id){
        Patch patch = patchService.loadPatchById(id);
        //ftpModel.addAttribute("ftp",ftp);
        return patch;
    }

    @RequestMapping(value = "/doUpdatePatch" , method = RequestMethod.POST)
    public String updatePatch(
            @RequestParam("id") int id,
            @RequestParam("patchname") String patchname,
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("script") String script,
            @RequestParam("path") String path,
            @RequestParam("host") String host
    ){
        Patch p = new Patch();
        p.setId(id);
        p.setPatch_name(patchname);
        p.setUser(user);
        p.setPassword(password);
        p.setScript(script);
        p.setHost(host);
        p.setPath(path);
        patchService.updatePatch(p);
        return "redirect:patch";
    }

}
