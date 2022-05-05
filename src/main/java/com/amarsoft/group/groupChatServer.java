package com.amarsoft.group;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class groupChatServer {
    private Selector selector;
    private ServerSocketChannel ListenerChannel;
    private static final int PORT = 1000;
    public groupChatServer(){
        try {
            selector = Selector.open();
            ListenerChannel = ServerSocketChannel.open();
            ListenerChannel.socket().bind(new InetSocketAddress(PORT));
            ListenerChannel.configureBlocking(false);
            ListenerChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Listener(){
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> key = selector.selectedKeys().iterator();
                    if (key.hasNext()) {
                        SelectionKey selectionKey = key.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel acceptChannel = ListenerChannel.accept();
                            acceptChannel.configureBlocking(false);
                            acceptChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(acceptChannel.getRemoteAddress()+"------上线了");
                        }
                        if (selectionKey.isReadable()){
                            readData(selectionKey);
                        }
                    }
                    key.remove();
                } else {
                    System.out.println("3秒内无事件发生，正在重试------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData(SelectionKey key) throws IOException {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0) {
                String message = new String(buffer.array());
                System.out.println("客户端:"+socketChannel.getRemoteAddress()+"-------发送消息:"+message.trim());
                sendMessage(message,socketChannel);
            }
        } catch (IOException e) {
            System.out.println("客户端:"+socketChannel.getRemoteAddress() + "-----------已离线");
            key.channel();
            socketChannel.close();
        }
    }

    private void sendMessage(String message,SocketChannel currentChannel) throws IOException{
        System.out.println("服务器转发消息中----------");
        for (SelectionKey key : selector.keys()){
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != currentChannel){
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                socketChannel.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        groupChatServer chatServer = new groupChatServer();
        chatServer.Listener();
    }
}
