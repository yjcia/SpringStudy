package com.spring.service;

import com.spring.model.WxMessage;

import java.io.PrintWriter;

/**
 * Created by YanJun on 2016/3/28.
 */
public interface IWeixinService {
    //public void generateAccessToken(String appid,String appsecret);
    String getAccessToken();

    <T> T prepareWxMessageBean(Class<T> clazz,String message);

    void sendReturnMessage(PrintWriter out,String message);

    WxMessage handleWxEventRequest(WxMessage receiveMessage,WxMessage eventMessage);

    String handleShowWeblogicEvent();
}
