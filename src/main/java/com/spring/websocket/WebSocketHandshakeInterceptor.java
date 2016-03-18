package com.spring.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
//http://www.open-open.com/lib/view/open1408453906131.html
//http://www.open-open.com/lib/view/open1448609470282.html
/**
 * Created by YanJun on 2016/3/18.
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
