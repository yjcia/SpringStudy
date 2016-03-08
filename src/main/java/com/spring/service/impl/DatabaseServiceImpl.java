package com.spring.service.impl;

import com.spring.dao.DatabaseDao;
import com.spring.dao.PatchDao;
import com.spring.model.Database;
import com.spring.model.Patch;
import com.spring.service.IDatabaseService;
import com.spring.service.IPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YanJun on 2016/3/7.
 */

@Service
public class DatabaseServiceImpl implements IDatabaseService {

    @Autowired
    private DatabaseDao databaseDao;


    public List<Database> findDatabaseList() {
        return databaseDao.findDatabaseList();
    }

    public void addDatabaseInfo(Database database) {
        databaseDao.addDatabaseInfo(database);
    }

    public void deleteDatabaseInfoById(String[] ids) {
        databaseDao.deleteDatabaseInfoById(ids);
    }

    public Database loadDatabaseById(int id) {
        return databaseDao.loadDatabaseById(id);
    }

    public void updateDatabase(Database database) {
        databaseDao.updateDatabase(database);
    }
}
