package com.amarsoft.protoTcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerInitizalize extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addFirst(new MessageDecoder());
        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(new ServerHandler());
    }
}
