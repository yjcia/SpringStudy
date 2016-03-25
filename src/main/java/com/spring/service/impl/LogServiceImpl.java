package com.spring.service.impl;

import com.spring.common.LogAttribute;
import com.spring.model.LogModel;
import com.spring.model.Storage;
import com.spring.service.ILogService;
import com.spring.util.StringUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by YanJun on 2016/3/25.
 */

@Service
public class LogServiceImpl implements ILogService {
    public List<LogModel> findLogList(String filePath) {
        int bufSize = 100;
        File fin = new File(filePath);
        FileChannel fcin;
        try {
            fcin = new RandomAccessFile(fin, "r").getChannel();
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
            return readFileByLine(bufSize, fcin, rBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LogModel> readFileByLine(int bufSize, FileChannel fcin, ByteBuffer rBuffer) {
        String enterStr = "\r\n";
        String enterStr2 = "\n";
        List<LogModel> logList = new ArrayList<LogModel>();
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
                    String line = tempString.substring(fromIndex, endIndex);
                    line = new String(strBuf.toString() + line);
                    //System.out.println(line);

                    if (sqlFindMode == 1 && line.indexOf(LogAttribute.USER_FLAG) >= 0) {
                        sqlFindMode = 0;
                    } else if (sqlFindMode == 1) {
                        sqlBuf.append(line).append(" ");
                    } else if (line.indexOf(LogAttribute.EXEC_SQL_FLAG) > 0
                            && line.indexOf(LogAttribute.USER_FLAG) < 0) {
                        sqlFindMode = 1;
                        sqlBuf.append(line.substring(line.indexOf(LogAttribute.EXEC_SQL_FLAG)));
                    }
                    if (execTimeMode == 1 && line.indexOf(LogAttribute.EXEC_TIME_FLAG) < 0) {
                        execTimeMode = 0;
                    } else if (execTimeMode == 1) {
                        execTimeBuf.append(line).append(" ");
                    } else if (line.indexOf(LogAttribute.EXEC_TIME_FLAG) > 0) {
                        execTimeMode = 1;
                        execTimeBuf.append(
                                line.substring(line.indexOf(LogAttribute.EXEC_TIME_FLAG),
                                        line.indexOf(LogAttribute.MS) + 2)
                        );
                    }

                    strBuf.delete(0, strBuf.length());
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
                if (rSize > tempString.length()) {
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }

            for (Integer key : sqlMap.keySet()) {
                LogModel log = new LogModel();
                log.setId(key);
                String execSqlStr = sqlMap.get(key);
                log.setExecSelectSql(execSqlStr.substring(execSqlStr.indexOf(":") + 1));
                String execTimeStr = execTimeMap.get(key);
                log.setExecTime(execTimeStr.substring(execTimeStr.indexOf(":") + 1,execTimeStr.indexOf(" MS")));
                logList.add(log);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(fcin != null) {
                    fcin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return logList;

    }
}
