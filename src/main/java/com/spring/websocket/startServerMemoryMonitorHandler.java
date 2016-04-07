package com.spring.websocket;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.model.Weblogic;
import com.spring.service.IServerService;
import com.spring.service.IWebSocketService;
import com.spring.service.IWeblogicService;
import com.spring.util.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class StartServerMemoryMonitorHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(StartServerMemoryMonitorHandler.class);
    private WebSocketSession webSocketSession = null;
    @Autowired
    private IServerService serverService;


    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.debug("connect to the websocket success .....");
        this.webSocketSession = webSocketSession;
        sendMemoryInfoMessage();

    }

    public void sendMemoryInfoMessage() throws IOException {
        String memoryInfo = serverService.calServerMemUsage();
        if(webSocketSession != null)
            webSocketSession.sendMessage(new TextMessage(memoryInfo));
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
        webSocketSession.close();

    }

    public boolean supportsPartialMessages() {
        return false;
    }
}
