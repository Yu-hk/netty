package com.amarsoft.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            String event = null;
            switch (stateEvent.state()){
                case READER_IDLE:
                    event = "读空闲";
                    break;
                case WRITER_IDLE:
                    event = "写空闲";
                    break;
                case ALL_IDLE:
                    event = "读写空闲";
                    break;
            }
            System.out.println("[客户端]"+ctx.channel().remoteAddress()+"超时-----"+event);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("客户端已断开连接-------");
    }
}
