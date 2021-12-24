package com.netty.adminServe.service.Impl;

import com.netty.adminServe.dao.MessageMapper;
import com.netty.adminServe.service.MessageService;
import com.netty.common.constants.Topic;
import com.netty.common.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/24
 * @描述
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    @Override
    public void insertInfo(Message Omessage) {
        com.netty.common.entity.Message message = new com.netty.common.entity.Message();
        message.setFromId(Omessage.getFromId());
        message.setToId(Omessage.getToId());
        message.setInfoContent(Omessage.getInfoContent());
        if(Omessage.getType().equals(Topic.OnLine))
        message.setOnline(true);
        else
            message.setOnline(false);
        message.setSingleMessage(Omessage.getState());
        message.setTime(new Date());

        System.out.println(message);

        messageMapper.insertInfo(message);
    }
}
