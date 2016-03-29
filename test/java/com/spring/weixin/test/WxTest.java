package com.spring.weixin.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.model.AccessToken;
import com.spring.model.WxMessage;
import com.spring.util.WxUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.*;

/**
 * Created by YanJun on 2016/3/28.
 */
public class WxTest {
    private static final String APPID = "wxa85db346322cb0c1";
    private static final String APPSECRET = "cfbb95c56b60c76f8021b5eb51ed8db9";
    @Test
    public void testGetAccessToken(){
        HttpGet httpget = new HttpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                        +APPID+"&secret="+APPSECRET);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true){
                String line = br.readLine();
                System.out.println(line);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                AccessToken token = gson.fromJson(line, AccessToken.class);
                if(line == null){
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAnalysisTextMsg(){
        String message = "<xml>" +
                "<ToUserName><![CDATA[gh_6b8ad83d3181]]></ToUserName>" +
                "<FromUserName><![CDATA[oYV3EwEwUfg4PYLUUBokswrnN_tY]]></FromUserName>" +
                "<CreateTime>1459221498</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[啊]]></Content>" +
                "<MsgId>6267308611933489090</MsgId>" +
                "</xml>";
        WxUtil.analysisWxMessage(WxMessage.class,message);
    }
    public void testGetMessage(){
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = null;
//        out = response.getWriter();
//        String signature = request.getParameter(WXAttribute.SIGNATURE);
//        String timestamp = request.getParameter(WXAttribute.TIMESTAMP);
//        String nonce = request.getParameter(WXAttribute.NONCE);
//        String echostr = request.getParameter(WXAttribute.ECHOSTR);
//        if (WxUtil.signCheck(signature,timestamp,nonce,echostr)) {
//            out.print(echostr);
//            //从请求中读取整个post数据
//            InputStream inputStream = request.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            while(true){
//                String line = br.readLine();
//                line = new String(line.getBytes("GBK"),"utf-8");
//                if (StringUtil.isEmpty(line)) break;
//                else {
//                    System.out.printf(line);
//                    out.write("<xml><" +
//                            "ToUserName><![CDATA[yjciausst]]></ToUserName>" +
//                            "<FromUserName><![CDATA[gh_6b8ad83d3181]]></FromUserName>" +
//                            "<CreateTime>1459218905</CreateTime>" +
//                            "<MsgType><![CDATA[text]]></MsgType>" +
//                            "<Content><![CDATA[你好]]></Content>" +
//                            "</xml>");
//                }
//            }
//        } else {
//            System.out.println("server valid error");
//        }
    }
}
