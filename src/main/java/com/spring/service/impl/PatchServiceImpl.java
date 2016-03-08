package com.spring.service.impl;

import com.spring.dao.FtpDao;
import com.spring.dao.PatchDao;
import com.spring.model.Ftp;
import com.spring.model.Patch;
import com.spring.service.IPatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YanJun on 2016/3/7.
 */

@Service
public class PatchServiceImpl implements IPatchService {

    @Autowired
    private PatchDao patchDao;

    public List<Patch> findPatchList() {
        return patchDao.findPatchList();
    }

    public void addPatchInfo(Patch patch) {
        patchDao.addPatchInfo(patch);
    }

    public void deletePatchInfoById(String[] ids) {
        patchDao.deletePatchInfoById(ids);
    }

    public Patch loadPatchById(int id) {
        return patchDao.loadPatchById(id);
    }

    public void updatePatch(Patch patch) {
        patchDao.updatePatch(patch);
    }
}
