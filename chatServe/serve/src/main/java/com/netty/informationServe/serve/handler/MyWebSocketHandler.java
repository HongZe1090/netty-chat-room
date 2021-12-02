package com.netty.informationServe.serve.handler;

import com.netty.informationServe.config.NettyConfig;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @创建人 HongZe
 * @创建时间 2021/11/22
 * @描述 接收/处理/响应客户端websocket请求的业务核心处理类  模板设计，子类重写某些类  SimpleChannelInboundHandler是一个模板类
 */
@Service
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";


//    服务端处理客户端websocket请求的核心方法
//    这是模板方法的实现
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
//        处理客户端向服务端发起http握手请求的业务
        if(msg instanceof FullHttpRequest) {
            handHttpRequest(channelHandlerContext,(FullHttpRequest) msg);
            logger.info("http 握手成功");
        }else if(msg instanceof WebSocketFrame) {  //处理websocket连接业务
            handWebsocketFrame(channelHandlerContext,(WebSocketFrame) msg);
        }

    }

    /**

     *@描述 处理客户端与服务端之间的业务

     */
    private void handWebsocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame){
        if(frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(),((CloseWebSocketFrame) frame).retain());
        }

//        判断是否时ping消息
        if(frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }

//        判断是否是二进制消息，如果是二进制消息，就抛出异常
        if(!(frame instanceof TextWebSocketFrame)) {
            System.out.println("目前我们不支持二进制消息");
            throw new RuntimeException(this.getClass().getName() + ":不支持消息");
        }
//        返回应答消息
//        获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端接收到客户端的消息--->"+request);
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "---->" + request);

//        群发，服务端向每个链接上来的客户端群发
        NettyConfig.group.writeAndFlush(tws);
    }

/**

 *@描述 客户端向服务端发起http握手请求的业务

 */
    private void handHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req) {
//        如果请求头中不含websocket，就不是握手请求
        if (!req.getDecoderResult().isSuccess()||!("websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wxFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);

        handshaker = wxFactory.newHandshaker(req);
        if(handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else {
            handshaker.handshake(ctx.channel(),req);
        }
    }

    /**

     *@描述 服务端向客户端响应消息

     */

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if(res.getStatus().code()!=200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
//        服务端向客户端发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if(res.getStatus().code()!=200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
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
