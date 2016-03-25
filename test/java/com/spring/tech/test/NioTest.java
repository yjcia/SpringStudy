package com.spring.tech.test;

import com.spring.util.StringUtil;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by YanJun on 2016/3/24.
 */
public class NioTest {
    @Test
    public void testReadLargeFile() {

        int bufSize = 100; //一次读取文件所缓存的字节数

        //得到日志文件
        File fin = new File("J:\\Code\\SpringStudy\\test\\java\\com\\spring\\tech\\test\\kewillfwd.log");
        FileChannel fcin = null;
        try {
            //建立管道
            fcin = new RandomAccessFile(fin, "r").getChannel();
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
            readFileByLine(bufSize, fcin, rBuffer);
            System.out.print("OK!!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void readFileByLine(int bufSize, FileChannel fcin, ByteBuffer rBuffer) {
        String enterStr = "\r\n";
        String enterStr2 = "\n";
        String dateTimeStr = "2016-03-24 08:30:14 INFO  [DBQuery]";
        try {
            int sqlFindMode = 0;
            int execTimeMode = 0;
            byte[] bs = new byte[bufSize];
            StringBuffer strBuf = new StringBuffer("");
            Map<Integer, String> sqlMap = new TreeMap<Integer, String>();
            Map<Integer, String> execTimeMap = new TreeMap<Integer, String>();
            StringBuffer sqlBuf = new StringBuffer("");
            StringBuffer execTimeBuf = new StringBuffer("");
            int sqlIndex = 0;
            int execTimeIndex = 0;
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize);
                int fromIndex = 0;
                int endIndex = 0;

                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1 ||
                        (endIndex = tempString.indexOf(enterStr2, fromIndex)) != -1) {
                    //如果有换行符，则截取到该换行符为止
                    String line = tempString.substring(fromIndex, endIndex);
                    line = new String(strBuf.toString() + line);
                    System.out.println(line);
                    //sql 获取
                    if (sqlFindMode == 1 && line.indexOf("User:") >= 0) {
                        sqlFindMode = 0;
                    } else if (sqlFindMode == 1) {
                        sqlBuf.append(line).append(" ");
                    } else if (line.indexOf("{EXEC SQL}") > 0 && line.indexOf("User:") < 0) {
                        sqlFindMode = 1;
                        sqlBuf.append(line.substring(line.indexOf("{EXEC SQL}")));
                    }

                    //exec time 获取
                    if (execTimeMode == 1 && line.indexOf("Query execute time") < 0) {
                        execTimeMode = 0;
                    } else if (execTimeMode == 1) {
                        execTimeBuf.append(line).append(" ");
                    } else if (line.indexOf("Query execute time") > 0) {
                        execTimeMode = 1;
                        execTimeBuf.append(line.substring(line.indexOf("Query execute time"), line.indexOf("MS") + 2));
                    }

                    strBuf.delete(0, strBuf.length());
                    //更新起始位置
                    fromIndex = endIndex + 1;
                }
                if (!StringUtil.isEmpty(sqlBuf.toString()) && sqlFindMode == 0) {
                    sqlMap.put(sqlIndex, sqlBuf.toString());
                    sqlBuf.delete(0, sqlBuf.length());
                    sqlIndex++;
                }
                if (!StringUtil.isEmpty(execTimeBuf.toString()) && execTimeMode == 0) {
                    execTimeMap.put(execTimeIndex, execTimeBuf.toString());
                    execTimeBuf.delete(0, execTimeBuf.length());
                    execTimeIndex++;
                }

                //将换行符后的部分追加到StringBuffer中从而继续使用
                if (rSize > tempString.length()) {
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }

            }
            System.out.println(execTimeMap);
            System.out.println(sqlMap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
