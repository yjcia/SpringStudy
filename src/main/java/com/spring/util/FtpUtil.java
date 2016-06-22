package com.spring.util;

import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by YanJun on 2016/6/22.
 */
public class FtpUtil {

    public static boolean uploadFileToFTP(String url, int port, String username,
                                          String password, String path, String filename,
                                          InputStream input) {
        boolean success = false;
        ChannelSftp sftp;
        Session sshSession;
        try {
            JSch jsch = new JSch();
            sshSession = jsch.getSession(username, url, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            sftp.cd(path);
            sftp.put(input,filename);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return success;
    }
}
