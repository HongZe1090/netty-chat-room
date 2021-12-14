package com.netty.adminServe.dao;

import com.netty.common.entity.LogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/14
 * @描述
 */
@Mapper
public interface LogMapper {
    /**

     *@描述 插入单条日志消息
     * @return

     */
    public boolean insertLog(LogInfo logInfo);

    /**

     *@描述 获取所有的日志消息

     */

    public List<LogInfo> getAllLog();
}
