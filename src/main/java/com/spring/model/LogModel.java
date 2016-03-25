package com.spring.model;

/**
 * Created by YanJun on 2016/3/25.
 */
public class LogModel {
    private int id;
    private String execSelectSql;
    private String execTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExecSelectSql() {
        return execSelectSql;
    }

    public void setExecSelectSql(String execSelectSql) {
        this.execSelectSql = execSelectSql;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }
}
