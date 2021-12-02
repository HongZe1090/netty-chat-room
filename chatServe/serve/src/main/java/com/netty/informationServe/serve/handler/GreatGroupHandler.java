package com.netty.informationServe.serve.handler;

import com.netty.informationServe.protocol.packet.CreateGroupPacket;
import com.netty.informationServe.protocol.packet.GroupMessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 创建群组处理器
 */
@Service
public class GreatGroupHandler extends SimpleChannelInboundHandler<CreateGroupPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupPacket createGroupPacket) throws Exception {

    }
}
