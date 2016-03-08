package com.spring.service.impl;

import com.spring.dao.FtpDao;
import com.spring.dao.UserDao;
import com.spring.model.Ftp;
import com.spring.service.IFtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */
@Service
public class FtpServiceImpl implements IFtpService {

    @Autowired
    private FtpDao ftpDao;

    public void addFtpInfo(Ftp ftp) {
        ftpDao.addFtpInfo(ftp);
    }

    public List<Ftp> findFtpList() {
        return ftpDao.findFtpList();
    }

    public void deleteFtpInfoById(String[] ids) {
        ftpDao.deleteFtpInfoById(ids);
    }

    public Ftp loadFtpById(int id) {
        return ftpDao.loadFtpById(id);
    }

    public void updateFtp(Ftp ftp) {
        ftpDao.updateFtp(ftp);
    }
}
