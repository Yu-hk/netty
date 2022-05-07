package com.amarsoft.protoTcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        System.out.println("解码开始----------");
        int length = in.readInt();
        byte[] buffer = new byte[length];
        in.readBytes(buffer);

        MessageProto proto = new MessageProto();
        proto.setLength(length);
        proto.setMessage(buffer);
        out.add(proto);
    }
}
