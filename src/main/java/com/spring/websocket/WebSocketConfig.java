package com.spring.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by YanJun on 2016/3/18.
 */
@WebAppConfiguration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    private WeblogicServerLogHandler weblogicServerLogHandler;
    @Autowired
    private StartWeblogicServerHandler startWeblogicServerHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(weblogicServerLogHandler, "/weblogicServerLog")
                .addInterceptors(new WebSocketHandshakeInterceptor());

        webSocketHandlerRegistry.addHandler(startWeblogicServerHandler, "/startWeblogic")
                .addInterceptors(new WebSocketHandshakeInterceptor());
    }


}
