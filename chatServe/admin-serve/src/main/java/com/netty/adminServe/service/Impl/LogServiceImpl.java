package com.netty.adminServe.service.Impl;

import com.netty.adminServe.dao.LogMapper;
import com.netty.adminServe.service.LogService;
import com.netty.common.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/14
 * @描述
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;

    @Override
    public void insertLog(LogInfo logInfo) {
        logMapper.insertLog(logInfo);
    }

    @Override
    public List<LogInfo> getAllLog() {
        return logMapper.getAllLog();
    }
}
