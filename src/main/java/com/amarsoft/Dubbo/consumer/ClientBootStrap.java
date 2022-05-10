package com.amarsoft.Dubbo.consumer;

import com.amarsoft.Dubbo.PubService.DubboServer;
import com.amarsoft.Dubbo.netty.NettyClient;

public class ClientBootStrap {

    public static final String providername = "network#";

    public static void main(String[] args) {
        NettyClient consumer = new NettyClient();
        DubboServer server = (DubboServer) consumer.getBean(DubboServer.class,providername);
        String message = server.start("hello");
        System.out.println("服务端返回结果:"+message);
    }
}
