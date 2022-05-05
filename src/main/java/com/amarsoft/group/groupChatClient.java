package com.amarsoft.group;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class groupChatClient {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 1000;
    private final Selector selector;
    private final SocketChannel socketChannel;

    public groupChatClient() throws Exception {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(IP, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        String username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username +"------已初始化");
    }

    public void sendInfo(String info){
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readInfo(){
        try {
            int count = selector.select();
            if (count > 0 ){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        String message = new String(buffer.array());
                        System.out.println("服务端返回消息:"+message);
                    }
                }
                iterator.remove();
            } else {
                System.out.println("当前无可用通道");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        groupChatClient chatClient = new groupChatClient();
        new Thread(() -> {
              while (true) {
                  chatClient.readInfo();
                  try {
                      Thread.sleep(3000);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            chatClient.sendInfo(scanner.nextLine());
        }
    }
}
