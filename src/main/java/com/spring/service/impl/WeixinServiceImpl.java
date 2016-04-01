package com.spring.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.common.WXAttribute;
import com.spring.model.AccessToken;
import com.spring.model.WxClick;
import com.spring.model.WxClickEvent;
import com.spring.model.WxMessage;
import com.spring.service.IWeixinService;
import com.spring.util.StringUtil;
import com.spring.util.WxUtil;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by YanJun on 2016/3/28.
 */

@Service
public class WeixinServiceImpl implements IWeixinService{
    private static Map<String,String> tokenMap = new HashMap<String,String>();
    private static final Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);
    private static Map<String,Object> eventMappingClassMap = new HashMap<String, Object>();
    public String generateAccessToken() {
        BufferedReader br = null;
        String tokenResult = null;
        try {
            br = WxUtil.httpGetToken();
            if(br != null){
                while(true){
                    String line = br.readLine();
                    if(!StringUtil.isEmpty(line)){
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        AccessToken token = gson.fromJson(line, AccessToken.class);
                        tokenResult = token.getAccess_token();
                        tokenMap.put(WXAttribute.TOKEN_KEY,token.getAccess_token());
                    }
                    if(line == null){
                        break;
                    }
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tokenResult;
    }

    public String getAccessToken() {
        String token;
        if(tokenMap.get(WXAttribute.TOKEN_KEY) != null){
            token = tokenMap.get(WXAttribute.TOKEN_KEY);
        }else{
            token = generateAccessToken();
        }
        return token;
    }

    public WxMessage handleWxEventRequest(WxMessage receiveMessage,WxMessage returnMessage) {
        SAXReader reader = new SAXReader();
        Object handleObject = null;
        //WxMessage returnResultMessage = null;
        try {
            Document document = reader.read(
                    this.getClass().getClassLoader().getResourceAsStream(WXAttribute.MAPPING_CONFIG));
            String xmlStr = document.asXML();
            System.out.println(xmlStr);
            XStream xStream = new XStream(new DomDriver());
            xStream.autodetectAnnotations(true);
            xStream.alias(WXAttribute.WXCLICK_NODE,WxClick.class);
            WxClick wxClick = (WxClick)xStream.fromXML(xmlStr);
            if(receiveMessage.getEvent().equals(wxClick.getEventName())){
                List<WxClickEvent> clickEventList = wxClick.getClickEventList();
                for(WxClickEvent event : clickEventList){
                    String eventKey = event.getEventKey();
                    logger.debug("weixin event:" + event);
                    if(eventKey.equals(receiveMessage.getEventKey())){
                        if(eventMappingClassMap.get(eventKey) != null){
                            handleObject = eventMappingClassMap.get(eventKey);
                        }
                        else{
                            String classFullName = event.getEventMapingClass();
                            Class clazz = Class.forName(classFullName);
                            System.out.println(clazz);
                            handleObject = clazz.newInstance();
                            eventMappingClassMap.put(eventKey,handleObject);
                        }
                        if(handleObject instanceof WeixinServiceImpl){
                            Method[] methods = handleObject.getClass().getDeclaredMethods();
                            for(Method method : methods){
                                if(event.getEventMapingMethod().equals(method.getName())){
                                    String returnResultStr = (String)method.invoke(handleObject);
                                    returnMessage.setContent(returnResultStr);
                                    returnMessage.setCreateTime(new Date().getTime());
                                }
                            }
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return returnMessage;
    }

    public String handleShowWeblogicEvent() {
        logger.debug("handleShowWeblogicEvent invoke !");
        return "weblogic";
    }

    public String handleShowStorageEvent() {
        logger.debug("handleShowStorageEvent invoke !");
        return "storage";
    }

    public String getAccessTokenNoChache() {
        String token;
        token = generateAccessToken();
        return token;
    }

    public <T> T prepareWxMessageBean(Class<T> clazz, String message) {
        return WxUtil.analysisWxMessage(clazz,message);
    }

    public void sendReturnMessage(PrintWriter out, String message) {
        out.write(message);
        out.flush();
        out.close();
    }
}
