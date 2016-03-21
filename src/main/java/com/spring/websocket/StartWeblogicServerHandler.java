package com.spring.websocket;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.model.Weblogic;
import com.spring.service.IWebSocketService;
import com.spring.service.IWeblogicService;
import com.spring.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class StartWeblogicServerHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(StartWeblogicServerHandler.class);
    private BufferedReader stdoutReader = null;
    private Session session = null;
    private StreamGobbler stdout = null;
    @Autowired
    private IWebSocketService webSocketService;
    @Autowired
    private IWeblogicService weblogicService;


    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.debug("connect to the websocket success .....");

        int id = Integer.parseInt(webSocketSession.getAttributes().get(SocketAttribute.WEBLOGIC_ID).toString());
        Weblogic weblogic = weblogicService.loadWeblogicById(id);

        session = webSocketService.getStartWeblogicLog(weblogic, webSocketSession);
        stdout = new StreamGobbler(session.getStdout());
        stdoutReader = new BufferedReader(
                new InputStreamReader(stdout));
        while (true) {
            String line = stdoutReader.readLine();
            if (line == null)
                break;
            webSocketSession.sendMessage(new TextMessage(line + "\n"));
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
        IOUtil.close(stdoutReader,stdout);

    }

    public boolean supportsPartialMessages() {
        return false;
    }
}
