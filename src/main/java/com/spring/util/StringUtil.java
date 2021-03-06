package com.spring.util;

/**
 * Created by YanJun on 2016/2/25.
 */
public class StringUtil {
    public static boolean isEmpty(String s){
        return s == null || "".equals(s);
    }

    public static String removeMultiSpaceToOne(String str){
        String s = "";
        for (int i = 0; i < str.length() - 1; i++) {
            //空格转成int型代表数字是32
            if ((int) str.charAt(i) == 32 && (int) str.charAt(i + 1) == 32) {
                continue;
            }
            s += str.charAt(i);
        }
        if ((int) str.charAt(str.length() - 1) != 32)
            s += str.charAt(str.length() - 1);
        return s;

    }
}
