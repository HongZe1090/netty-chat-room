实现初次握手的处理
```java
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
            // Unpooled是netty对Buffet的封装
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
```