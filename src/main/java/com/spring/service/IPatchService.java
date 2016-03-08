package com.spring.service;

import com.spring.model.Ftp;
import com.spring.model.Patch;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */
public interface IPatchService {
    List<Patch> findPatchList();

    void addPatchInfo(Patch patch);

    void deletePatchInfoById(String[] ids);

    Patch loadPatchById(int id);

    void updatePatch(Patch patch);
}
