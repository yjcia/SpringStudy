package com.spring.service.impl;

import com.spring.dao.WebSocketDao;
import com.spring.dao.WeblogicDao;
import com.spring.service.IWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YanJun on 2016/3/18.
 */

@Service
public class WebSocketService implements IWebSocketService {

    @Autowired
    private WebSocketDao webScoketDao;

    public void getCurrentWeblogicLog() {
        webScoketDao.getCurrentWeblogicLog();
    }
}
