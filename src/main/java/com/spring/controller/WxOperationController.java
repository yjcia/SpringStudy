package com.spring.controller;

import com.spring.model.WxMessage;
import com.spring.service.IWeixinService;
import com.spring.util.StringUtil;
import com.spring.util.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by YanJun on 2016/3/28.
 */

@Controller
public class WxOperationController {

    private static final Logger logger = LoggerFactory.getLogger(WxOperationController.class);
    @Autowired
    private IWeixinService weixinService;

    @RequestMapping(value="/wx",method = RequestMethod.GET)
    public void valid(@RequestParam("signature") String signature,
                      @RequestParam("timestamp") String timestamp,
                      @RequestParam("nonce") String nonce,
                      @RequestParam("echostr") String echostr,HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (WxUtil.signCheck(signature,timestamp,nonce,echostr)) {
                out.print(echostr);
            } else {
                System.out.println("server valid error");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value="/wxGetMsg",method = RequestMethod.POST)
    public void getTesterClientMessage(HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = WxUtil.getReceiveMessage(request);
            WxMessage wMessage = weixinService.prepareWxMessageBean(WxMessage.class,message);
            logger.debug("receive message ---> " + wMessage);

//          out.write("success");
            out.write("<xml>" +
                    "<ToUserName><![CDATA[oYV3EwEwUfg4PYLUUBokswrnN_tY]]></ToUserName>" +
                    "<FromUserName><![CDATA[gh_6b8ad83d3181]]></FromUserName>" +
                    "<CreateTime>1459220131</CreateTime>" +
                    "<MsgType><![CDATA[text]]></MsgType>" +
                    "<Content><![CDATA[你好]]></Content>" +
                    "</xml>");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/wx",method = RequestMethod.POST)
    public void getClientMessage(HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = WxUtil.getReceiveMessage(request);
            WxMessage wMessage = weixinService.prepareWxMessageBean(WxMessage.class,message);
            logger.debug("receive message ---> " + wMessage);

//          out.write("success");
            out.write("<xml>" +
                    "<ToUserName><![CDATA[owrTqsr9_KT5kaackeeJ9p8SK9Xc]]></ToUserName>" +
                    "<FromUserName><![CDATA[gh_26e03d2fdde0]]></FromUserName>" +
                    "<CreateTime>1459220131</CreateTime>" +
                    "<MsgType><![CDATA[text]]></MsgType>" +
                    "<Content><![CDATA[你好]]></Content>" +
                    "</xml>");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
