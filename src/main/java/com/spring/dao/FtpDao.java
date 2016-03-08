package com.spring.dao;

import com.spring.model.Ftp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */

@Repository
public interface FtpDao {
    List<Ftp> findFtpList();

    void addFtpInfo(Ftp ftp);

    void deleteFtpInfoById(String[] ids);

    Ftp loadFtpById(int id);

    void updateFtp(Ftp ftp);
}
