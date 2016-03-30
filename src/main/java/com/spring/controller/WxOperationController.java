package com.spring.controller;

import com.spring.common.WXAttribute;
import com.spring.model.WxMessage;
import com.spring.service.IWeixinService;
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
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by YanJun on 2016/3/28.
 */

@Controller
public class WxOperationController {

    private static final Logger logger = LoggerFactory.getLogger(WxOperationController.class);
    @Autowired
    private IWeixinService weixinService;

    @RequestMapping(value = "/wx", method = RequestMethod.GET)
    public void valid(@RequestParam("signature") String signature,
                      @RequestParam("timestamp") String timestamp,
                      @RequestParam("nonce") String nonce,
                      @RequestParam("echostr") String echostr, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (WxUtil.signCheck(signature, timestamp, nonce, echostr)) {
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

    //测试账号接口认证
    @RequestMapping(value = "/wxGetMsg", method = RequestMethod.POST)
    public void getTesterClientMessage(HttpServletRequest request,
                                       HttpServletResponse response) {
        getClientMessage(request, response);

    }

    @RequestMapping(value = "/wx", method = RequestMethod.POST)
    public void getClientMessage(HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = WxUtil.encodeReceiveMessage(request);
            WxMessage wMessage = weixinService.prepareWxMessageBean(WxMessage.class, message);
            logger.debug("receive message ---> " + wMessage);
            WxMessage returnMessage = new WxMessage();
            returnMessage.setToUserName(wMessage.getFromUserName());
            returnMessage.setFromUserName(wMessage.getToUserName());
            returnMessage.setCreateTime(new Date().getTime());
            returnMessage.setEventKey(wMessage.getEventKey());
            returnMessage.setEvent(wMessage.getEvent());

            if (wMessage.getMsgType().equals(WXAttribute.MSGTYPE_TEXT)) {
                handleTextMessage(out, returnMessage);

            } else if (wMessage.getMsgType().equals(WXAttribute.MSGTYPE_EVENT)) {
                handleEventMessage(out, returnMessage);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void handleEventMessage(PrintWriter out, WxMessage returnMessage)
            throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        returnMessage.setMsgType(WXAttribute.MSGTYPE_TEXT);
        returnMessage.setContent("你好");
        weixinService.sendReturnMessage(out, WxUtil.generateReturnMessage(returnMessage));
    }

    private void handleTextMessage(PrintWriter out, WxMessage returnMessage)
            throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        returnMessage.setMsgType(WXAttribute.MSGTYPE_TEXT);
        returnMessage.setContent("你好");
        weixinService.sendReturnMessage(out, WxUtil.generateReturnMessage(returnMessage));
    }
}
