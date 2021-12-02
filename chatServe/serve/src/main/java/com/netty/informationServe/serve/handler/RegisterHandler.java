package com.netty.informationServe.serve.handler;

import com.netty.informationServe.protocol.packet.RegisterPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 注册处理器
 */
@Service
public class RegisterHandler extends SimpleChannelInboundHandler<RegisterPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterPacket registerPacket) throws Exception {

    }
}
