package com.spring.service;

import com.spring.model.Ftp;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */
public interface IFtpService {
    List<Ftp> findFtpList();

    void addFtpInfo(Ftp ftp);

    void deleteFtpInfoById(String[] ids);

    Ftp loadFtpById(int id);

    void updateFtp(Ftp ftp);
}
