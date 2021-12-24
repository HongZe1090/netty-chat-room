package com.netty.adminServe.controller;

import com.alibaba.fastjson.JSON;
import com.netty.adminServe.service.Impl.LogServiceImpl;
import com.netty.adminServe.service.Impl.MessageServiceImpl;
import com.netty.common.domain.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/8
 * @描述 消息队列的消息
 */

@Component
@RocketMQMessageListener(
        topic = "NettyInfo",
        consumerGroup = "NettyInfo",
        selectorExpression = "*"
)
public class MessageConsumer implements RocketMQListener<Object> {
    @Autowired
    MessageServiceImpl messageService;

    @Override
    public void onMessage(Object message) {
        System.out.println(message);
        Message message1 =  JSON.parseObject((String) message, Message.class);
        messageService.insertInfo(message1);
    }
}