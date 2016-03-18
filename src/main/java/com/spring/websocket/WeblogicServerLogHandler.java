package com.spring.websocket;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.service.IWebSocketService;
import com.spring.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class WeblogicServerLogHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(WeblogicServerLogHandler.class);

    @Autowired
    private IWebSocketService webSocketService;

    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.debug("connect to the websocket success .....");
//        for(int i=0 ;i<10 ;i++){
//            webSocketSession.sendMessage(new TextMessage("hello websocket:" + i + "\n"));
//        }
        Connection conn = null;
        Session session = null;
        InputStream stdout = null;
        BufferedReader br = null;
        String scriptShell = null;
        try {
            conn = new Connection("10.104.46.200");
            ConnectionInfo info = conn.connect();
            boolean result = conn.authenticateWithPassword("weblogic", "als");
            if(result) {
                Session sess = conn.openSession();
                sess.execCommand("tail -f /data2/domains/kef_trunk_8009/nohup.out");
                stdout = new StreamGobbler(sess.getStdout());
                BufferedReader stdoutReader = new BufferedReader(
                        new InputStreamReader(stdout));
                while (true) {
                    String line = stdoutReader.readLine();
                    if (line == null)
                        break;
                    webSocketSession.sendMessage(new TextMessage(line + "\n"));
                    //scriptShell += (line+"\n");
                    //System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.close(br,stdout,session,conn);

        }
    }

    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if(webSocketMessage.toString().equals("close")){
            webSocketSession.close();
        }
    }

    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        logger.debug("websocket connection closed......");

    }

    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
    }

    public boolean supportsPartialMessages() {
        return false;
    }
}
