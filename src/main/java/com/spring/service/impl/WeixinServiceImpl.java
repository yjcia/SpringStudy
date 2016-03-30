package com.spring.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.common.WXAttribute;
import com.spring.model.AccessToken;
import com.spring.model.WxMessage;
import com.spring.service.IWeixinService;
import com.spring.util.StringUtil;
import com.spring.util.WxUtil;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanJun on 2016/3/28.
 */

@Service
public class WeixinServiceImpl implements IWeixinService{
    private static Map<String,String> tokenMap = new HashMap<String,String>();
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

    public void handleWxEventRequest(WxMessage eventMessage) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("src\\main\\resources\\conf\\eventMapping.xml"));
            String xmlStr = document.asXML();
            System.out.println(xmlStr);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

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
