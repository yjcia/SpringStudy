package com.spring.model;

/**
 * Created by YanJun on 2016/2/29.
 */
public class Weblogic {
    private int id;
    private String name;
    private String path;
    private String rspath;
    private String host;
    private String remark;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRspath() {
        return rspath;
    }

    public void setRspath(String rspath) {
        this.rspath = rspath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Weblogic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", rspath='" + rspath + '\'' +
                ", host='" + host + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
