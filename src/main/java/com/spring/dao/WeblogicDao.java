package com.spring.dao;

import com.spring.model.Patch;
import com.spring.model.Weblogic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */

@Repository
public interface WeblogicDao {
    List<Weblogic> findWeblogicList();

    void addWeblogicInfo(Weblogic w);

    void deleteWeblogicInfoById(String[] ids);

    Weblogic loadWeblogicById(int id);

    void updateWeblogic(Weblogic w);
}
