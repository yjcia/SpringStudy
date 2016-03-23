package com.spring.tech.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YanJun on 2016/3/22.
 */
public class StringUtilTest {

    @Test
    public void testSplit(){
        String str = "/dev/sda2                         148G   95G   47G  68% /";
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
        System.out.println(s);
    }

}
