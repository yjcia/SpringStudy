package com.spring.service;

import com.spring.model.LogModel;
import com.spring.model.Storage;

import java.util.List;

/**
 * Created by YanJun on 2016/3/22.
 */
public interface ILogService {
    List<LogModel> findLogList(String filePath);
}
