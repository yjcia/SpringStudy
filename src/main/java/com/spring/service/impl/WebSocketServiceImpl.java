package com.spring.service.impl;


import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.model.Weblogic;
import com.spring.service.IWebSocketService;
import com.spring.util.IOUtil;
import com.spring.util.WebSocketUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class WebSocketServiceImpl implements IWebSocketService {

    public Session getCurrentWeblogicLog(Weblogic weblogic,WebSocketSession webSocketSession) {
        Session session = null;
        try {
            session = WebSocketUtil.getConnection(weblogic.getHost(),SocketAttribute.WEBLOGIC_USERNAME,
                    SocketAttribute.WEBLOGIC_PWD).openSession();
            session.execCommand(getShowLogCmd(weblogic));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
            session = WebSocketUtil.getConnection(weblogic.getHost(),SocketAttribute.WEBLOGIC_USERNAME,
                    SocketAttribute.WEBLOGIC_PWD).openSession();
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

    public Session getStartWeblogicLog(Weblogic weblogic, WebSocketSession webSocketSession) {
        return null;
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
}
