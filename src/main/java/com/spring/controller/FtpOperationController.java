package com.spring.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.spring.model.Ftp;
import com.spring.model.ProcessInfo;
import com.spring.model.User;
import com.spring.service.IFtpService;
import com.spring.service.IUserService;
import com.spring.util.FtpUtil;
import com.spring.util.ParameterUtil;
import com.spring.util.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
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

    @RequestMapping(value="/doUploadFtp",method = RequestMethod.POST)
    public ModelAndView uploadFtpFile(
            HttpServletRequest request,HttpServletResponse response){
        final HttpSession hs = request.getSession();
        ModelAndView mv =  new ModelAndView();
        String uploadDestPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
        Map<String,String> paramMap = new HashMap<String, String>();
        try {

            //Map<String,String> paramMap = ParameterUtil.getParameterFromRequestStream(request.getInputStream());
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if(!isMultipart){
                return mv;
            }
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int pItems) {
                    ProcessInfo pri = new ProcessInfo();
                    pri.itemNum = pItems;
                    pri.readSize = pBytesRead;
                    pri.totalSize = pContentLength;
                    pri.show = Math.round(new Float(pBytesRead) / new Float(pContentLength)*100) +"%";
                    pri.rate = Math.round(new Float(pBytesRead) / new Float(pContentLength)*100);
                    hs.setAttribute("proInfo", pri);
                }
            });
            List<FileItem> items = upload.parseRequest(request);
            InputStream uploadFileStream;
            for(FileItem item : items){
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    paramMap.put(name,value);
                } else {
                    logger.debug("this is file feild!");
                    String fileName = item.getName();
                    File uploadedFile = new File(uploadDestPath + "\\" + fileName);
                    item.write(uploadedFile);
                    uploadFileStream = new FileInputStream(uploadedFile);
                    FtpUtil.uploadFileToFTP(paramMap.get("hostname"),22,paramMap.get("user"),
                            paramMap.get("password"),paramMap.get("path"),fileName,uploadFileStream);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;

    }
    @RequestMapping(value = "/getProgress", method = RequestMethod.GET)
    @ResponseBody
    public Object process(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        return request.getSession().getAttribute("proInfo");
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
