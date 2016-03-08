package com.spring.dao;

import com.spring.model.Database;
import com.spring.model.Patch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */

@Repository
public interface DatabaseDao {
    List<Database> findDatabaseList();

    void addDatabaseInfo(Database database);

    void deleteDatabaseInfoById(String[] ids);

    Database loadDatabaseById(int id);

    void updateDatabase(Database database);
}
