package com.netty.informationServe.serve.handler;

import com.alibaba.fastjson.JSONObject;
import com.netty.common.domain.User;
import com.netty.informationServe.protocol.packet.CreateGroupPacket;
import com.netty.informationServe.protocol.packet.GroupMessagePacket;
import com.netty.informationServe.utils.SessionUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/2
 * @描述 创建群组处理器
 */
@Service
@ChannelHandler.Sharable
public class GreatGroupHandler extends SimpleChannelInboundHandler<CreateGroupPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupPacket createGroupPacket) throws Exception {
        List<String> userIdList = createGroupPacket.getUserIdList();
        List<String> nameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());
        channelGroup.add(channelHandlerContext.channel());
        //用户名是否可以不添加？？
        nameList.add(SessionUtils.getUser(channelHandlerContext.channel()).getUserName());
        //根据id获得绑定的channel，并将channel放入channelGroup中
        for (String userId : userIdList) {
            Integer it = Integer.valueOf(userId);
            Channel channel = SessionUtils.getChannel(it);
            User user = SessionUtils.getUser(channel);
            if (channel != null) {
                channelGroup.add(channel);
                nameList.add(user.getUserName());
            }
        }
        String groupId = UUID.randomUUID().toString();
        // 绑定群Id 和 channelgroup的映射
        Integer it = Integer.valueOf(groupId);
        SessionUtils.bindChannelGroup(it, channelGroup);
        ByteBuf byteBuf = getByteBuf(channelHandlerContext, groupId, nameList);
        channelGroup.writeAndFlush(new TextWebSocketFrame(byteBuf));
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx, String groupId, List<String> nameList) {
        ByteBuf bytebuf = ctx.alloc().buffer();
        JSONObject data = new JSONObject();
        data.put("type", 4);
        data.put("status", 200);
        data.put("groupId", groupId);
        data.put("nameList", nameList);
        byte []bytes = data.toJSONString().getBytes(Charset.forName("utf-8"));
        bytebuf.writeBytes(bytes);
        return bytebuf;
    }
}
