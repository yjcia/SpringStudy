package com.spring.util;

import com.spring.annotation.DataAttribute;
import com.spring.common.WXAttribute;
import com.spring.model.WxMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by YanJun on 2016/3/28.
 */
public class WxUtil {
    public static BufferedReader httpGetToken(){
        String urlProd = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + WXAttribute.APPID+"&secret="+WXAttribute.APPSECRET;
        String urlTest = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + WXAttribute.APPID_TEST+"&secret="+WXAttribute.APPSECRET_TEST;
        HttpGet httpget = new HttpGet(urlTest);
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

    public static String encodeReceiveMessage(HttpServletRequest request){
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

    public static String generateReturnMessage(WxMessage returnMessage)
            throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        XStream xstream = new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    boolean cdata = false;
                    Class<?> targetClass = null;
                    @SuppressWarnings("unchecked")
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                        if (!name.equals("xml")) {
                            // 对带有注解NeedDataFormatter的xml节点都增加CDATA标记
                            cdata = needCData(targetClass,name);
                        }else{
                            targetClass = clazz;
                        }
                    }
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        xstream.autodetectAnnotations(true);
        String xml = xstream.toXML(returnMessage);
        return xml;
    }

    public static boolean needCData(Class<?> targetClass,String name) {
        boolean needCData = false;
        if(targetClass != null){
            String fieldName = name.substring(0,1).toLowerCase()+name.substring(1);
            Field[] fields = targetClass.getDeclaredFields();
            for(Field field:fields){
                if(field.isAnnotationPresent(DataAttribute.NeedDataFormatter.class)
                        && fieldName.equals(field.getName())){
                    DataAttribute.NeedDataFormatter DataAttribute =
                            field.getAnnotation(DataAttribute.NeedDataFormatter.class);
                    boolean needDataAttribute = DataAttribute.value();
                    if(needDataAttribute){
                        needCData = true;
                    }
                }
            }
        }

        return needCData;
    }


}
