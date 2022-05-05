package com.amarsoft.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
          ctx.channel().eventLoop().execute(() -> {
              try {
                  Thread.sleep(2000);
                  ctx.writeAndFlush(Unpooled.copiedBuffer("线程重新启动-----",CharsetUtil.UTF_8));
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          });
          ctx.channel().eventLoop().schedule(() -> {
              try {
                  ctx.writeAndFlush(Unpooled.copiedBuffer("线程延时启动------",CharsetUtil.UTF_8));
              } catch (Exception e) {
                  e.printStackTrace();
              }
          },5, TimeUnit.SECONDS);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已上线",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
