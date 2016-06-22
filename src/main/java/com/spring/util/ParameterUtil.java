package com.spring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YanJun on 2016/6/22.
 */
public class ParameterUtil {
    public static Map<String,String> getParameterFromRequestStream(InputStream paramStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(paramStream));

        String line = null;
        String key = null;
        //String value = null;
        Map<String,String> paramMap = new HashMap<String, String>();
        try {
            while ((line = reader.readLine()) != null) {
                if(line.contains("name")){
                    Pattern p = Pattern.compile("\"(.*?)\"");
                    Matcher m = p.matcher(line);
                    while(m.find()){
                       key = m.group();
                    }
                }else if(!line.startsWith("Content") && !line.startsWith("------")){
                    String value = line.trim();
                    if(!StringUtil.isEmpty(value)){
                        paramMap.put(key,value);
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paramMap;
    }
}
