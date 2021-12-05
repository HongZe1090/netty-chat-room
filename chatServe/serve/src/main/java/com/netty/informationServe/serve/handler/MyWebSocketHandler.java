package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSON;
import com.netty.common.domain.User;
import com.netty.informationServe.config.NettyConfig;
import com.netty.informationServe.protocol.Packet;
import com.netty.informationServe.protocol.packet.CreateGroupPacket;
import com.netty.informationServe.protocol.packet.GroupMessagePacket;
import com.netty.informationServe.protocol.packet.RegisterPacket;
import com.netty.informationServe.protocol.packet.SingleMessagePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/22
 * @描述 接收/处理/响应客户端websocket请求的业务核心处理类  模板设计，子类重写某些类  SimpleChannelInboundHandler是一个模板类
 */
@Service
@ChannelHandler.Sharable
public class MyWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    服务端处理客户端websocket请求的核心方法
//    这是模板方法的实现
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame msg) throws Exception {

            handWebsocketFrame(channelHandlerContext, msg);

    }

    /**

     *@描述 处理客户端与服务端之间的业务

     */
    private void handWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        if(frame instanceof CloseWebSocketFrame) {
            ctx.channel().close();
        }

//        判断是否时ping消息
        if(frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }

////        判断是否是二进制消息，如果是二进制消息，就抛出异常
//        if(!(frame instanceof BinaryWebSocketFrame)) {
//            System.out.println("目前我们不支持二进制消息");
//            throw new RuntimeException(this.getClass().getName() + ":不支持消息");
//        }

        TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) frame;
        ByteBuf bytebuf = textWebSocketFrame.content();
//从content中写入缓冲区
        String content = bytebuf.toString(Charset.forName("utf-8"));
        JSONObject jsonObject = JSONObject.parseObject(content);
//将json字符串转变为json对象
        logger.info(content);
//从json对象中按属性取值
        Byte type = jsonObject.getByte("type");
        JSONObject parmas = jsonObject.getJSONObject("params");
        Packet packet = null;

        switch (type) {
            // 注册user-->channel 映射
            case 7:
                RegisterPacket registerRequestPacket = new RegisterPacket();
//                将json对象转换为实体类
                User user =  JSON.parseObject(parmas.toJSONString(), User.class);
                registerRequestPacket.setUser(user);
                packet = registerRequestPacket;
                break;
            // 单聊
            case 1:
                SingleMessagePacket messageRequestPacket = new SingleMessagePacket();
                messageRequestPacket.setMessage(parmas.getString("message"));
                messageRequestPacket.setToUserId(parmas.getString("toMessageId"));
                messageRequestPacket.setFileType(parmas.getString("fileType"));
                packet = messageRequestPacket;
                break;
            // 创建群聊
            case 3:
                CreateGroupPacket createGroupRequestPacket = new CreateGroupPacket();
                String userListStr = parmas.getString("userIdList");
                List<String> userIdList = Arrays.asList(userListStr.split(","));
                createGroupRequestPacket.setUserIdList(userIdList);
                packet = createGroupRequestPacket;
                break;
            // 群聊消息
            case 9:
                GroupMessagePacket groupMessageRequestPacket = new GroupMessagePacket();
                groupMessageRequestPacket.setMessage(parmas.getString("message"));
                groupMessageRequestPacket.setToGroupId(parmas.getString("toMessageId"));
                groupMessageRequestPacket.setFileType(parmas.getString("fileType"));
                packet = groupMessageRequestPacket;
                break;
            //心跳检测 暂未添加
            default:
                break;
        }

        //        返回应答消息
//        获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame) frame).text();
//        System.out.println("服务端接收到客户端的消息--->"+request);
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "---->" + request);
        ctx.writeAndFlush(tws);


        ctx.fireChannelRead(packet);



    }

    //客户端与服务端创建连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端连接开启....");
    }

    //客户端与服务端断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端连接关闭....");
    }

    //接收结束之后 read相对于服务端
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

//    出现异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
