package com.spring.util;

import com.spring.common.WXAttribute;
import com.spring.model.WxMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;

/**
 * Created by YanJun on 2016/3/28.
 */
public class WxUtil {
    public static BufferedReader httpGetToken(){
        HttpGet httpget = new HttpGet(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                        + WXAttribute.APPID+"&secret="+WXAttribute.APPSECRET);
        BufferedReader br = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();
            br = new BufferedReader(new InputStreamReader(is));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static boolean signCheck(String signature,String timestamp,String nonce,String echostr){
        String[] values = { WXAttribute.TOKEN, timestamp, nonce };
        Arrays.sort(values);
        String value = values[0] + values[1] + values[2];
        String signStr = DigestUtils.sha1Hex(value);
        return signature.equals(signStr);

    }

    public static String getReceiveMessage(HttpServletRequest request){
        StringBuffer receiveMessage = new StringBuffer();
        try{
        InputStream inputStream = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            String line = br.readLine();
            if (StringUtil.isEmpty(line)) break;
            else {
                line = new String(line.getBytes("GBK"), "utf-8");
                receiveMessage.append(line);
            }
        }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMessage.toString();
    }

    public static <T> T analysisWxMessage(Class<T> clazz,String message){
        T t = null;
        XStream xStream = new XStream(new DomDriver());
        xStream.autodetectAnnotations(true);
        try {
            t = clazz.newInstance();
            if(t instanceof WxMessage){
                xStream.processAnnotations(t.getClass());
                t = (T)xStream.fromXML(message);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
