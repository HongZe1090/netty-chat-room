package com.netty.adminServe.controller;

import com.netty.common.constants.Topic;
import com.netty.common.domain.LoginInfo;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/11
 * @描述 日志消息消费者
 */
@Component
@RocketMQMessageListener(
        topic = "NettyLog",
        consumerGroup = "NettyLog",
        selectorExpression = "*"
)
public class LogConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String logInfo) {
        System.out.println("received message is {}" + logInfo);
    }
}
