package com.spring.controller;

import ch.ethz.ssh2.*;
import com.spring.model.Ftp;
import com.spring.model.Patch;
import com.spring.service.IFtpService;
import com.spring.service.IPatchService;
import com.spring.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


    @RequestMapping(value = "/showPatchScript" , method = RequestMethod.POST)
    @ResponseBody
    public String showPatchScript(
            @RequestParam("user") String user,
            @RequestParam("password") String password,
            @RequestParam("script") String script,
            @RequestParam("path") String path,
            @RequestParam("host") String host
    ){
        Connection conn = null;
        Session session = null;
        InputStream stdout = null;
        BufferedReader br = null;
        String scriptShell = "";
        try {
            conn = new Connection(host);
            ConnectionInfo info = conn.connect();
            boolean result = conn.authenticateWithPassword(user, password);
            if(result) {
                Session sess = conn.openSession();
                sess.execCommand("cat "+ path + "/" + script);
                stdout = new StreamGobbler(sess.getStdout());
                BufferedReader stdoutReader = new BufferedReader(
                        new InputStreamReader(stdout));
                while (true) {
                    String line = stdoutReader.readLine();
                    if (line == null)
                        break;
                    scriptShell += (line+"\n");
                    //System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.close(br,stdout,session,conn);

        }
        return scriptShell;
    }
}
