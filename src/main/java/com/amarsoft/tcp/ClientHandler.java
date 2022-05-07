package com.amarsoft.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端开始发送消息-----");
        for (int i = 0; i < 5; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("客户端发送消息-----" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(byteBuf);
        }
        System.out.println("客户端发送消息结束-----");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        String message = new String(buffer,CharsetUtil.UTF_8);
        System.out.println("客户端接收到消息:"+message);
        System.out.println("当前接收的消息数量:"+(++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端出现异常!");
    }

}
