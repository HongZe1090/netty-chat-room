package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.RegisterPacket;
import com.netty.informationServe.protocol.packet.SingleMessagePacket;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 私聊消息处理器
 */
@Service
public class SingleMessageHandler extends SimpleChannelInboundHandler<SingleMessagePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SingleMessagePacket singleMessagePacket) throws Exception {
// TODO Auto-generated method stub
        String message = "";
        Channel toUserChannel = SessionUtils.getChannel(singleMessagePacket.getToUserId());
        if (toUserChannel != null && SessionUtils.hasLogin(toUserChannel)) {
            message = singleMessagePacket.getMessage();
        } else {
            message = "当前用户："+singleMessagePacket.getToUserId()+"不在线！";
            System.err.println(message);
        }
        User toUser = SessionUtils.getUser(toUserChannel);
        String fileType = singleMessagePacket.getFileType();
        ByteBuf buf = getByteBuf(channelHandlerContext, message, toUser, fileType);
        toUserChannel.writeAndFlush(new TextWebSocketFrame(buf));
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

}
