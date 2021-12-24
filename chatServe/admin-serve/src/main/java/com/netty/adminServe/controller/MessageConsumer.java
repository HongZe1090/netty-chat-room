package com.netty.adminServe.controller;

import com.alibaba.fastjson.JSON;
import com.netty.common.domain.Message;
import com.netty.common.entity.LogInfo;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
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
    @Override
    public void onMessage(Object message) {
        System.out.println(message);
        Message message1 =  JSON.parseObject((String) message, Message.class);
//        logService.insertLog(info);
        System.out.println(message1.getInfoContent());
        System.out.println("received message is {}" + message1.getClass().getName());
    }
}