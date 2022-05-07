package com.amarsoft.InBondAndOutBond;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitizalize extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addFirst(new MyDecoder());
        pipeline.addLast(new MyEncode());
        pipeline.addLast(new MyInBondHandler());
    }
}
