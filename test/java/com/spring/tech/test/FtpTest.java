package com.spring.tech.test;

import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by YanJun on 2016/6/22.
 */
public class FtpTest {

    @Test
    public void testConnectFtp(){
        try {
            ChannelSftp sftp = null;
            JSch jsch = new JSch();

            Session sshSession = jsch.getSession("kff16", "10.104.46.200", 22);
            System.out.println("Session created.");
            sshSession.setPassword("kff16");
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}
