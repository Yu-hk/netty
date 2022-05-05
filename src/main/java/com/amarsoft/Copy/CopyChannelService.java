package com.amarsoft.Copy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class CopyChannelService {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(1000));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int count = 0;
            while (count != -1){
                try {
                    count = socketChannel.read(buffer);
                } catch (IOException e) {
                    break;
                }
                buffer.rewind();
            }
        }
    }
}
