package com.spring.model;

/**
 * Created by YanJun on 2016/6/22.
 */
public class ProcessInfo {
    public long totalSize = 1;
    public long readSize = 0;
    public String show = "";
    public int itemNum = 0;
    public int rate = 0;

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getReadSize() {
        return readSize;
    }

    public void setReadSize(long readSize) {
        this.readSize = readSize;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
