package com.spring.service;

/**
 * Created by YanJun on 2016/3/28.
 */
public interface IWeixinService {
    //public void generateAccessToken(String appid,String appsecret);
    public String getAccessToken();

    public <T> T prepareWxMessageBean(Class<T> clazz,String message);
}
