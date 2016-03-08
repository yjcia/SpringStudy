package com.spring.model;

/**
 * Created by YanJun on 2016/2/25.
 */
public class Ftp {
    private int id;
    private String host_name;
    private String user;
    private String password;
    private String port;
    private String path;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostName() {
        return host_name;
    }

    public void setHostName(String host_name) {
        this.host_name = host_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Ftp{" +
                "id=" + id +
                ", hostName='" + host_name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", port='" + port + '\'' +
                ", path='" + path + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
