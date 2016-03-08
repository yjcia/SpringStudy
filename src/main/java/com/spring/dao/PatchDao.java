package com.spring.dao;

import com.spring.model.Ftp;
import com.spring.model.Patch;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YanJun on 2016/2/29.
 */

@Repository
public interface PatchDao {
    List<Patch> findPatchList();

    void addPatchInfo(Patch patch);

    void deletePatchInfoById(String[] ids);

    Patch loadPatchById(int id);

    void updatePatch(Patch patch);
}
