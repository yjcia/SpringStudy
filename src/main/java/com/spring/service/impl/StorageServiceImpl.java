package com.spring.service.impl;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.spring.common.SocketAttribute;
import com.spring.dao.StorageDao;
import com.spring.model.Storage;
import com.spring.service.IStorageService;
import com.spring.util.IOUtil;
import com.spring.util.StringUtil;
import com.spring.util.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

@Service("storageServiceImpl")
public class StorageServiceImpl implements IStorageService {

    @Autowired
    private StorageDao storageDao;

    public List<Storage> findStorageList() {
        return storageDao.findStorageList();
    }
}
