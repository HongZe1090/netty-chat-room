package com.netty.informationServe;

import com.netty.common.domain.Message;
import com.netty.informationServe.serve.WebSocketServe;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**

 *@描述 CommandLineRunner、ApplicationRunner 接口是在容器启动成功后的最后一步回调（类似开机自启动）。

 */

@SpringBootApplication
public class ChatServeApplication implements CommandLineRunner {

    @Autowired
    WebSocketServe serve;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ChatServeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Message message = new Message();
//        message.setFromId(1);
//        message.setInfoContent("465");
//        rocketMQTemplate.convertAndSend("NettyInfo",message);
//        System.out.println("消息已经发送拉...");
        serve.run();
    }
}
