package com.amarsoft.InBondAndOutBond;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyInBondHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("服务端开始发送数据");
        ctx.writeAndFlush(2342342L);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) {
        System.out.println("从客户端读取数据:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("服务端服务异常!");
    }
}
