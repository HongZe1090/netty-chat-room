package com.netty.adminServe.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netty.adminServe.service.Impl.LogServiceImpl;
import com.netty.adminServe.service.LogService;
import com.netty.common.entity.LogInfo;
import com.netty.common.domain.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LogConsumer implements RocketMQListener<Object> {
    @Autowired
    LogServiceImpl logService;

    @Override
    public void onMessage(Object logInfo) {
        System.out.println(logInfo);
        LogInfo info =  JSON.parseObject((String) logInfo, LogInfo.class);
        logService.insertLog(info);
    }
}
