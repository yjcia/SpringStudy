package com.spring.tech.test;

import ch.ethz.ssh2.*;
import com.spring.model.User;
import com.spring.service.IUserService;
import com.spring.util.IOUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by YanJun on 2016/2/1.
 */

public class UserDaoTest {

    @Test
    public void testReadRemoteLog(){
        Connection conn = null;
        Session session = null;
        InputStream stdout = null;
        BufferedReader br = null;
        try {
            conn = new Connection("10.104.46.200");
            ConnectionInfo info = conn.connect();
            boolean result = conn.authenticateWithPassword("kff17", "kff17");
            session = conn.openSession();
            //session.execCommand("cd /data1/domains/kef_v513_8513");
            //5.得到脚本运行成功与否的标志 ：0－成功 非0－失败
            //System.out.println("ExitCode: " + session.getExitStatus());
            //session.close();
            //接收目标服务器上的控制台返回结果，读取br中的内容
            session.execCommand("vi /data2/kff_pack_tools_svn/kff_trunk_17/quick_deploy_weblogic.sh");
            stdout = new StreamGobbler(session.getStdout());
            br = new BufferedReader(new InputStreamReader(stdout));
            String line = null;
            while((line=br.readLine())!=null){
                System.out.println(line);
                //System.out.println("ExitCode: " + session.getExitStatus());
                //br.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.close(br,stdout,session,conn);
        }
    }

    @Test
    public void testSFTPReadRemoteFile(){
        Connection conn = null;
        Session session = null;
        InputStream stdout = null;
        BufferedReader br = null;
        String scriptShell = null;
        try {
            conn = new Connection("10.104.46.200");
            ConnectionInfo info = conn.connect();
            boolean result = conn.authenticateWithPassword("kff17", "kff17");
            if(result) {
                Session sess = conn.openSession();
                sess.execCommand("cat /data2/kff_pack_tools_svn/kff_trunk_17/quick_deploy_weblogic.sh");
                stdout = new StreamGobbler(sess.getStdout());
                BufferedReader stdoutReader = new BufferedReader(
                        new InputStreamReader(stdout));
                while (true) {
                    String line = stdoutReader.readLine();
                    if (line == null)
                        break;
                    scriptShell += (line+"\n");
                    //System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           IOUtil.close(br,stdout,session,conn);

        }
        System.out.println(scriptShell);
    }


}
