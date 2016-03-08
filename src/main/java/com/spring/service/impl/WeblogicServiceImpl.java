package com.spring.service.impl;

import com.spring.dao.PatchDao;
import com.spring.dao.WeblogicDao;
import com.spring.model.Patch;
import com.spring.model.Weblogic;
import com.spring.service.IPatchService;
import com.spring.service.IWeblogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YanJun on 2016/3/7.
 */

@Service
public class WeblogicServiceImpl implements IWeblogicService {

    @Autowired
    private WeblogicDao weblogicDao;

    public List<Weblogic> findWeblogicList() {
        return weblogicDao.findWeblogicList();
    }

    public void addWeblogicInfo(Weblogic w) {
        weblogicDao.addWeblogicInfo(w);
    }

    public void deleteWeblogicInfoById(String[] ids) {
        weblogicDao.deleteWeblogicInfoById(ids);
    }

    public Weblogic loadWeblogicById(int id) {
        return weblogicDao.loadWeblogicById(id);
    }

    public void updateWeblogic(Weblogic w) {
        weblogicDao.updateWeblogic(w);
    }
}
