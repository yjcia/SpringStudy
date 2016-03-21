package com.spring.service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.dao.WebSocketDao;
import com.spring.dao.WeblogicDao;
import com.spring.model.Weblogic;
import com.spring.service.IWebSocketService;
import com.spring.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class WebSocketServiceImpl implements IWebSocketService {
    private static Map<String,Connection> connMap = new HashMap<String,Connection>();

    public Session getCurrentWeblogicLog(Weblogic weblogic,WebSocketSession webSocketSession) {
        Session session = null;
        try {
            session = getConnection(weblogic).openSession();
            session.execCommand(getShowLogCmd(weblogic));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //IOUtil.close(stdoutReader,stdout,session,conn);
        }
        return session;
    }

    /**
     * 启动weblogic遇到些问题，待探究。。。。
     * @param weblogic
     * @param webSocketSession
     * @return
     */
    public Session getStartWeblogicLog(Weblogic weblogic,WebSocketSession webSocketSession) {
        Session session = null;
        try {
            session = getConnection(weblogic).openSession();
            session.execCommand("./data1/domains/kef_v520_pub_7522/startWeblogic.sh " +
                    "| tail -f /data1/domains/kef_v520_pub_7522/nohup.out");
//            session.execCommand("cd " + weblogic.getPath());
//            if(session.getExitStatus() == 0){
//                session.close();
//                session = getConnection(weblogic).openSession();
//                session.requestPTY("startWeblogicLog", 80, 24, 640, 480, null);
//                Thread.sleep(4000);
//                session.execCommand(getStartWeblogicCmd(weblogic));
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //IOUtil.close(stdoutReader,stdout,session,conn);
        }
        return session;
    }
    public int getCurrentWeblogicStatus(Weblogic weblogic) {
        Session session = null;
        InputStream stdout = null;
        BufferedReader stdoutReader = null;
        int result = 0;
        try {
            session = getConnection(weblogic).openSession();
            session.execCommand(getWeblogicStatusCmd(weblogic));
            stdout = new StreamGobbler(session.getStdout());
            stdoutReader = new BufferedReader(new InputStreamReader(stdout));
            while (true)
            {
                String line = stdoutReader.readLine();
                if(line != null && line.indexOf("weblogic.Server") > 0){
                    result = 1;
                    break;
                }
                if (line == null)
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.close(stdoutReader,stdout,session);
        }
        return result;
    }

    private String getShowLogCmd(Weblogic weblogic){
        if(weblogic != null)
            return "tail -f " + weblogic.getPath()+"/"+SocketAttribute.WEBLOGIC_NOHUP;
        return "";
    }

    private String getWeblogicStatusCmd(Weblogic weblogic){
        String name = weblogic.getName();
        String[] params = name.split("_");
        String port = params[params.length-1];
        return "ps -ef|grep " + port;
    }

    private String getStartWeblogicCmd(Weblogic weblogic){

        String startScript = SocketAttribute.WEBLOGIC_START_SCRIPT + ";" + SocketAttribute.WEBLOGIC_NOHUP_SCRIPT;
        return startScript;
    }

    private Connection getConnection(Weblogic weblogic) {
        Connection conn = null;
        try {
            if (connMap.get(weblogic.getName()) != null) {
                return connMap.get(weblogic.getName());
            } else {
                conn = new Connection(weblogic.getHost());
                ConnectionInfo info = conn.connect();
                boolean result = conn.authenticateWithPassword(SocketAttribute.WEBLOGIC_USERNAME,
                        SocketAttribute.WEBLOGIC_PWD);
                if (result) {
                    connMap.put(weblogic.getName(), conn);
                } else {
                    connMap.put(weblogic.getName(), null);
                    throw new IOException("Authentication failed.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
