package com.spring.websocket;

import com.spring.service.IWebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

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
        webSocketService.getCurrentWeblogicLog();
    }

    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }

    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    public boolean supportsPartialMessages() {
        return false;
    }
}
