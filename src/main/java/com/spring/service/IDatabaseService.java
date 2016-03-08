package com.spring.service;

import com.spring.model.Database;
import com.spring.model.Patch;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */
public interface IDatabaseService {
    List<Database> findDatabaseList();

    void addDatabaseInfo(Database database);

    void deleteDatabaseInfoById(String[] ids);

    Database loadDatabaseById(int id);

    void updateDatabase(Database database);
}
