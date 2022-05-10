package com.amarsoft.Dubbo.provider;

import com.amarsoft.Dubbo.netty.NettyServer;

public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",4000);
    }
}
