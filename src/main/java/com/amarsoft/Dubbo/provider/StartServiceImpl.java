package com.amarsoft.Dubbo.provider;

import com.amarsoft.Dubbo.PubService.DubboServer;

public class StartServiceImpl implements DubboServer {
    @Override
    public String start(String message) {
        System.out.println("服务端收到消息:"+message);
        if (message == null) {
            return "客户端未发送消息";
        } else {
            return "客户端发送消息:"+message;
        }
    }
}
