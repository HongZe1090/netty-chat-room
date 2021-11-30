package com.netty.chatserve;

import com.netty.chatserve.serve.WebSocketServe;
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

    public static void main(String[] args) {
        SpringApplication.run(ChatServeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        serve.run();
    }
}
