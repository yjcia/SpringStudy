package com.spring.model;

/**
 * Created by YanJun on 2016/4/6.
 */
public class Server {
    private String hostName;
    private String username;
    private String password;
    private int usedMem;
    private int totalMem;
    private int freeMem;
    private double cpuForUser;
    private double cpuForSys;
    private double cpuForIdle;

    public Server(){}
    public Server(String hostName, String username, String password) {
        this.hostName = hostName;
        this.username = username;
        this.password = password;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsedMem() {
        return usedMem;
    }

    public void setUsedMem(int usedMem) {
        this.usedMem = usedMem;
    }

    public int getTotalMem() {
        return totalMem;
    }

    public void setTotalMem(int totalMem) {
        this.totalMem = totalMem;
    }

    public int getFreeMem() {
        return freeMem;
    }

    public void setFreeMem(int freeMem) {
        this.freeMem = freeMem;
    }

    public double getCpuForUser() {
        return cpuForUser;
    }

    public void setCpuForUser(double cpuForUser) {
        this.cpuForUser = cpuForUser;
    }

    public double getCpuForSys() {
        return cpuForSys;
    }

    public void setCpuForSys(double cpuForSys) {
        this.cpuForSys = cpuForSys;
    }

    public double getCpuForIdle() {
        return cpuForIdle;
    }

    public void setCpuForIdle(double cpuForIdle) {
        this.cpuForIdle = cpuForIdle;
    }
}
