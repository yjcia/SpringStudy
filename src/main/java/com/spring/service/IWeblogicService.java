package com.spring.service;

import com.spring.model.Patch;
import com.spring.model.Weblogic;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */
public interface IWeblogicService {
    List<Weblogic> findWeblogicList();

    void addWeblogicInfo(Weblogic w);

    void deleteWeblogicInfoById(String[] ids);

    Weblogic loadWeblogicById(int id);

    void updateWeblogic(Weblogic w);
}
