package com.netty.informationServe.utils;

import com.netty.common.domain.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/10
 * @描述
 */
@Component
public class MQUtils {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void MessageSend(String topic, Message message) {
        rocketMQTemplate.convertAndSend("NettyInfo",message);
        System.out.println("消息已经发送拉...");
    }

}
