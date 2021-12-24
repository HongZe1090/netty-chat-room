package com.netty.adminServe.dao;

import com.netty.common.entity.LogInfo;
import com.netty.common.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/24
 * @描述
 */
@Mapper
public interface MessageMapper {
    /**

     *@描述 插入单条消息记录
     * @return

     */
    public boolean insertInfo(Message insertInfo);
}
