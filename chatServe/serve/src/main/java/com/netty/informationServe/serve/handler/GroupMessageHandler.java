package com.netty.informationServe.serve.handler;

import com.netty.informationServe.protocol.packet.GroupMessagePacket;
import com.netty.informationServe.protocol.packet.RegisterPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 群组消息处理器
 */
@Service
public class GroupMessageHandler extends SimpleChannelInboundHandler<GroupMessagePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessagePacket groupMessagePacket) throws Exception {

    }
}
