package com.amarsoft.protoTcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<MessageProto> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProto msg, ByteBuf out) {
        System.out.println("编码开始----------");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getMessage());
    }
}
