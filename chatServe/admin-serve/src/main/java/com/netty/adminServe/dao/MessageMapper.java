package com.netty.adminServe.dao;

import com.netty.common.entity.LogInfo;
import com.netty.common.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    /**

     *@描述 获取所有的日志消息

     */

    public List<Message> getAllInfo();

    /**

     *@描述 根据用户id与获取用户

     */
    public List<com.netty.common.entity.Message> getMessage(Integer fromId,Integer toId);
}
