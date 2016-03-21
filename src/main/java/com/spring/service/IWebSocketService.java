package com.spring.service;

import ch.ethz.ssh2.Session;
import com.spring.model.Weblogic;
import org.springframework.web.socket.WebSocketSession;

import java.io.BufferedReader;

/**
 * Created by YanJun on 2016/3/18.
 */
public interface IWebSocketService {
    Session getCurrentWeblogicLog(Weblogic weblogic, WebSocketSession webSocketSession);

    int getCurrentWeblogicStatus(Weblogic weblogic);

    Session getStartWeblogicLog(Weblogic weblogic, WebSocketSession webSocketSession);
}
