package com.spring.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import com.spring.common.SocketAttribute;
import com.spring.model.Weblogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YanJun on 2016/3/22.
 */
public class WebSocketUtil {
    private static Map<String,Connection> connMap = new HashMap<String,Connection>();
    public static Connection getConnection(String host,String username,String password) {

        Connection conn = null;
        try {
            if (connMap.get(host) != null) {
                return connMap.get(host);
            } else {
                conn = new Connection(host);
                ConnectionInfo info = conn.connect();
                boolean result = conn.authenticateWithPassword(username,password);
                if (result) {
                    connMap.put(host, conn);
                } else {
                    boolean resultAgain = conn.authenticateWithPassword(username,SocketAttribute.WEBLOGIC_PWD_2);
                    if(resultAgain){
                        connMap.put(host, conn);
                    }else{
                        connMap.put(host, null);
                        throw new IOException("Authentication failed.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
