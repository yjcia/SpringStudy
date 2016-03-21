package com.spring.websocket;

import com.spring.common.SocketAttribute;
import com.spring.model.Weblogic;
import com.spring.service.IWeblogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
//http://www.open-open.com/lib/view/open1408453906131.html
//http://www.open-open.com/lib/view/open1448609470282.html
/**
 * Created by YanJun on 2016/3/18.
 */

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpServletRequest request = servletRequest.getServletRequest();
            int weblogicId = Integer.parseInt(request.getParameter(SocketAttribute.WEBLOGIC_ID));
            map.put(SocketAttribute.WEBLOGIC_ID,weblogicId);
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
