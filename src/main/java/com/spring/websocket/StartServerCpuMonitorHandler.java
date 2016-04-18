package com.spring.websocket;

import com.spring.service.IServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class StartServerCpuMonitorHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(StartServerCpuMonitorHandler.class);
    private WebSocketSession webSocketSession = null;
    @Autowired
    private IServerService serverService;


    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.debug("connect to the websocket success .....");
        this.webSocketSession = webSocketSession;
        sendCpuInfoMessage();

    }

    public void sendCpuInfoMessage() throws IOException {
        String cpuInfo = serverService.calServerCpuUsage();
        if(webSocketSession != null && webSocketSession.isOpen())
            webSocketSession.sendMessage(new TextMessage(cpuInfo));
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
        //webSocketSession.close();

    }

    public boolean supportsPartialMessages() {
        return false;
    }
}
