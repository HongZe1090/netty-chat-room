package com.netty.adminServe.service;

import com.netty.common.entity.LogInfo;
import com.netty.common.model.base.RestMsg;
import com.netty.common.model.form.AuthBody;

import java.util.List;
import java.util.Set;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/14
 * @描述
 */
public interface LogService {
    /**

     *@描述 插入单条日志消息

     */
    public void insertLog(LogInfo logInfo);

    /**

     *@描述 获取所有的日志消息

     */
    public List<LogInfo> getAllLog();
}
