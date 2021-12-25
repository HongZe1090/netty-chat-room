package com.netty.adminServe.service;

import com.netty.common.domain.Message;
import com.netty.common.entity.LogInfo;

import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/24
 * @描述
 */
public interface MessageService {
    /**

     *@描述 插入单条消息记录

     */
    public void insertInfo(Message Omessage);

    /**

     *@描述 获得所有的消息记录

     */
    public List<com.netty.common.entity.Message> getAllInfo();

    /**

     *@描述 根据用户id与获取用户

     */
    public List<com.netty.common.entity.Message> getMessage(Integer fromId,Integer toId);
}
