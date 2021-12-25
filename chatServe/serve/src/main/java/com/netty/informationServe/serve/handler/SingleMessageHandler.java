package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSONObject;
import cn.hutool.core.date.DateTime;
import com.netty.common.constants.Topic;
import com.netty.common.domain.Message;
import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.SingleMessagePacket;
import com.netty.common.config.MQUtils;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 私聊消息处理器
 */
@Service
@ChannelHandler.Sharable
public class SingleMessageHandler extends SimpleChannelInboundHandler<SingleMessagePacket> {
    @Autowired
    MQUtils mqUtils;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SingleMessagePacket singleMessagePacket) throws Exception {
// TODO Auto-generated method stu
//        onLine标志字符为下面使用那个消息队列标志
        Boolean onLine;
        String message = "";
        Channel toUserChannel = SessionUtils.getChannel(singleMessagePacket.getToUserId());
        System.out.println(SessionUtils.userIdChannelMap);
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            message = singleMessagePacket.getMessage();
            sendMessage(channelHandlerContext,message, singleMessagePacket.getToUserId(), Topic.OnLine,true);
        } else {
            message = singleMessagePacket.getMessage();
            sendMessage(channelHandlerContext,message, singleMessagePacket.getToUserId(), Topic.OffLine,true);
            System.err.println(message);
        }
        User toUser = SessionUtils.getUser(toUserChannel);
        String fileType = singleMessagePacket.getFileType();
        ByteBuf buf = getByteBuf(channelHandlerContext, message, toUser, fileType);

        toUserChannel.writeAndFlush(new TextWebSocketFrame(buf));

        System.out.println(singleMessagePacket.getToUserId() + "发送了消息给" + singleMessagePacket.getToUserId() + "：" + singleMessagePacket.getMessage());
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx, String message, User toUser, String fileType) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        User fromUser = SessionUtils.getUser(ctx.channel());
        JSONObject data = new JSONObject();
        data.put("type", 2);
        data.put("status", 200);
        JSONObject params = new JSONObject();
        params.put("message", message);
        params.put("fileType", fileType);
        params.put("fromUser", fromUser);
        params.put("toUser", toUser);
        data.put("params", params);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public void sendMessage(ChannelHandlerContext ctx, String message, Integer toUser, String state, Boolean type) {
        Message messageMQ = new Message();
        messageMQ.setFromId(SessionUtils.getUser(ctx.channel()).getUserId());
        messageMQ.setToId(toUser);
        messageMQ.setType(state);
        messageMQ.setInfoContent(message);
        messageMQ.setTime(new DateTime().toString());
        messageMQ.setState(type);

        mqUtils.MessageSend(Topic.OnLineTopic,messageMQ);
    }

}
