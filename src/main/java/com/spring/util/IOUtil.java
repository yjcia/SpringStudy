package com.spring.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by YanJun on 2016/3/17.
 */
public class IOUtil {
    public static void close(BufferedReader br, InputStream stdout, Session session, Connection conn){
        try {
            if(br != null)
                br.close();
            if(stdout != null)
                stdout.close();
            if(session != null)
                session.close();
            if(conn != null)
                conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
