package com.netty.adminServe.service;

import com.netty.common.domain.Message;
import com.netty.common.entity.LogInfo;

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
}
