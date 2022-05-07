package com.amarsoft.InBondAndOutBond;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyOutBondHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) {
        System.out.println("从服务端读取数据:"+msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端开始发送数据");
        ctx.writeAndFlush(123412L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("服务端服务异常!");
    }
}
