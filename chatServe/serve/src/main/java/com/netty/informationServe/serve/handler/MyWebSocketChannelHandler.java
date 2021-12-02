package com.netty.informationServe.serve.handler;

import com.netty.informationServe.serve.handler.MyWebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/30
 * @描述 责任链机制，handler传入的参数类型必须与继承的父类传入的泛型一致，否则不会被执行；可以借此来选择handler
 */
@Service
public class MyWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {

    @Autowired
    MyWebSocketHandler myWebSocketHandler;

    @Autowired
    RegisterHandler registerHandler;

    @Autowired
    SingleMessageHandler singleMessageHandler;

    @Autowired
    GroupMessageHandler groupMessageHandler;

    @Autowired
    GreatGroupHandler creatGroupHandler;

    @Override
    protected void initChannel(SocketChannel e) throws Exception {
        e.pipeline().addLast("http-codec", new HttpServerCodec()); //http编解码
        e.pipeline().addLast("aggregator",new HttpObjectAggregator(65536)); //httpContent消息聚合
        e.pipeline().addLast("http-chunked",new ChunkedWriteHandler());  // HttpContent 压缩
        e.pipeline().addLast("base_handler",myWebSocketHandler)
                    .addLast("register_handler",registerHandler)
                    .addLast("single_message",singleMessageHandler)
                    .addLast("creat_group",creatGroupHandler)
                    .addLast("group_message",groupMessageHandler)
//                    .addLast(HeartBeatRequestHandler.INSTANCE)
                    .addLast(ExceptionHandler.INSTANCE);
    }
}
