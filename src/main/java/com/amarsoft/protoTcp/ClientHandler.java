package com.amarsoft.protoTcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class ClientHandler extends SimpleChannelInboundHandler<MessageProto> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端已上线");
        for (int i = 0; i < 5; i++) {
            String message = "客户端发送消息-"+i;
            byte[] buf = message.getBytes(CharsetUtil.UTF_8);
            int length = message.getBytes(CharsetUtil.UTF_8).length;

            MessageProto messageProto = new MessageProto();
            messageProto.setLength(length);
            messageProto.setMessage(buf);
            ctx.writeAndFlush(messageProto);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProto msg) {
        int length = msg.getLength();
        byte[] message = msg.getMessage();
        System.out.println("客户端接收数据:"+"数据大小:"+length + ",数据内容:"+new String(message, CharsetUtil.UTF_8));
        System.out.println("当前客户端已发送消息包:"+(++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端异常----------");
        ctx.close();
    }
}
