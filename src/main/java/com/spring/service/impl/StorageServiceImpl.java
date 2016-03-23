package com.spring.service.impl;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.model.Storage;
import com.spring.service.IStorageService;
import com.spring.util.IOUtil;
import com.spring.util.StringUtil;
import com.spring.util.WebSocketUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanJun on 2016/3/22.
 */

@Service
public class StorageServiceImpl implements IStorageService {
    private List<Storage> sTempList = new ArrayList<Storage>();
    public StorageServiceImpl(){
        sTempList.add(new Storage("10.104.46.200","root","Kewill@200"));
        sTempList.add(new Storage("10.104.46.203","root","Kewill@203"));
        sTempList.add(new Storage("10.104.46.205","root","Kewill@205"));
        sTempList.add(new Storage("10.104.46.197","root","Kewill@197"));
        sTempList.add(new Storage("10.104.46.195","root","Kewill@195"));
    }
    public List<Storage> findStorageList() {
        Session session = null;
        InputStream stdout = null;
        BufferedReader stdoutReader = null;
        List<Storage> resultList = new ArrayList<Storage>();
        try {

            for(Storage s:sTempList){
                session = WebSocketUtil.getConnection(
                        s.getHostName(), s.getUserName(), s.getPassword()).openSession();
                session.execCommand(SocketAttribute.STORAGE_USE_SCRIPT);
                stdout = new StreamGobbler(session.getStdout());
                stdoutReader = new BufferedReader(new InputStreamReader(stdout));
                int lineNumber = 1;
                while (true)
                {
                    String line = stdoutReader.readLine();
                    if(line != null && lineNumber != 1){
                        String sInfoStr = StringUtil.removeMultiSpaceToOne(line);
                        String[] sInfoArr = sInfoStr.split(" ");
                        Storage storage = new Storage();
                        storage.setHostName(s.getHostName());
                        storage.setFileSystem(sInfoArr[0]);
                        storage.setSize(sInfoArr[1]);
                        storage.setUsed(sInfoArr[2]);
                        storage.setAvail(sInfoArr[3]);
                        storage.setUse(sInfoArr[4].substring(0,sInfoArr[4].lastIndexOf("%")));
                        storage.setMounted(sInfoArr[5]);
                        resultList.add(storage);
                    }
                    if (line == null) break;
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
}
