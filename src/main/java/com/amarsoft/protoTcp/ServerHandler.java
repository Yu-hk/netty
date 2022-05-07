package com.amarsoft.protoTcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class ServerHandler extends SimpleChannelInboundHandler<MessageProto> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("服务端已上线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto msg) throws Exception {
        int length = msg.getLength();
        byte[] message = msg.getMessage();

        System.out.println("服务端接收数据:"+"数据大小:"+length + ",数据内容:"+new String(message, CharsetUtil.UTF_8));
        System.out.println("当前客户端已发送消息包:"+(++this.count));

        String sendmsg = UUID.randomUUID().toString();
        int sendLen = sendmsg.getBytes(CharsetUtil.UTF_8).length;
        byte[] buf = sendmsg.getBytes(CharsetUtil.UTF_8);
        MessageProto messageProto = new MessageProto();
        messageProto.setLength(sendLen);
        messageProto.setMessage(buf);
        ctx.writeAndFlush(messageProto);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端异常---------");
        ctx.close();
    }

}
