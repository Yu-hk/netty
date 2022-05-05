package com.amarsoft.Copy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import static java.lang.Math.ceil;

public class CopyChannelClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",1000));
        String filename = "";
        FileChannel fileChannel = new FileInputStream(filename).getChannel();
        int count = (int) ceil((float) fileChannel.size() / (1024*1024*8));
        System.out.println("开始打印:"+System.currentTimeMillis());
        for (int i = 0; i < count; i++){
            long position = (long) i *1024*1024*8;
            fileChannel.transferTo(position,fileChannel.size(),socketChannel);
            System.out.println(System.currentTimeMillis());
        }
        fileChannel.close();
    }
}
