package com.spring.dao;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.model.Server;
import com.spring.model.Storage;
import com.spring.util.IOUtil;
import com.spring.util.StringUtil;
import com.spring.util.WebSocketUtil;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanJun on 2016/4/6.
 */

@Repository
public class ServerDao {
    private List<Server> sTempList = new ArrayList<Server>();

    public ServerDao(){
        sTempList.add(new Server("10.104.46.195","root","Kewill@195"));
        sTempList.add(new Server("10.104.46.197","root","Kewill@197"));
        sTempList.add(new Server("10.104.46.198","root","Kewill@198"));
        sTempList.add(new Server("10.104.46.199","root","Kewill@199"));
        sTempList.add(new Server("10.104.46.200","root","Kewill@200"));
        sTempList.add(new Server("10.104.46.203","root","Kewill@203"));
        sTempList.add(new Server("10.104.46.205","root","Kewill@205"));
    }
    public List<Server> getCurrentServerMemUsage(){
        Session session = null;
        InputStream stdout = null;
        BufferedReader stdoutReader = null;
        List<Server> resultList = new ArrayList<Server>();
        try {

            for(Server s:sTempList){
                session = getSession(s);
                stdoutReader = getReaderFromCommand(session,SocketAttribute.FREE_MEM_SCRIPT);
                int lineNumber = 1;
                while (true)
                {
                    String line = stdoutReader.readLine();
                    if(line != null && lineNumber == 2){
                        String sInfoStr = StringUtil.removeMultiSpaceToOne(line);
                        String[] sInfoArr = sInfoStr.split(" ");
                        Server server = new Server();
                        server.setHostName(s.getHostName());
                        server.setTotalMem(Integer.parseInt(sInfoArr[1]));
                        server.setUsedMem(Integer.parseInt(sInfoArr[2]));
                        server.setFreeMem(Integer.parseInt(sInfoArr[3]));
                        resultList.add(server);
                        break;
                    }
                    lineNumber++;
                }
                IOUtil.close(stdoutReader,stdout,session);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.close(stdoutReader,stdout,session);
        }

        return resultList;
    }

    public List<Server> getCurrentServerCpuUsage(){
        Session session = null;
        InputStream stdout = null;
        BufferedReader stdoutReader = null;
        List<Server> resultList = new ArrayList<Server>();
        try {

            for(Server s:sTempList){
                session = getSession(s);
                stdoutReader = getReaderFromCommand(session,SocketAttribute.FREE_CPU_SCRIPT);
                int lineNumber = 1;
                while (true)
                {
                    String line = stdoutReader.readLine();
                    if(line != null && lineNumber == 4){
                        String sInfoStr = StringUtil.removeMultiSpaceToOne(line);
                        String[] sInfoArr = sInfoStr.split(" ");
                        Server server = new Server();
                        server.setHostName(s.getHostName());
                        server.setCpuForUser(Double.parseDouble(sInfoArr[3]));
                        server.setCpuForSys(Double.parseDouble(sInfoArr[5]));

                        resultList.add(server);
                        break;
                    }else if(line == null){
                        break;
                    }
                    lineNumber++;
                }
                IOUtil.close(stdoutReader,stdout,session);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.close(stdoutReader,stdout,session);
        }

        return resultList;
    }

    private BufferedReader getReaderFromCommand(Session session,String command) throws IOException {
        session.execCommand(command);
        InputStream stdout = new StreamGobbler(session.getStdout());
        BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
        return stdoutReader;
    }

    private Session getSession(Server server) throws IOException {
        return WebSocketUtil.getConnection(
                server.getHostName(), server.getUsername(), server.getPassword()).openSession();
    }
}
