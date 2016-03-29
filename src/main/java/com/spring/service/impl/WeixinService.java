package com.spring.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.common.WXAttribute;
import com.spring.model.AccessToken;
import com.spring.service.IWeixinService;
import com.spring.util.StringUtil;
import com.spring.util.WxUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanJun on 2016/3/28.
 */

@Service
public class WeixinService implements IWeixinService{
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

    public <T> T prepareWxMessageBean(Class<T> clazz, String message) {
        return WxUtil.analysisWxMessage(clazz,message);
    }
}
