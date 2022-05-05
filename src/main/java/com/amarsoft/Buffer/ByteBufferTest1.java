package com.amarsoft.Buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Random;

public class ByteBufferTest1 {
    public static void main(String[] args) {
        Random random = new Random();
        ByteBuf buffer = Unpooled.buffer(random.nextInt(20));
        for (int i = 0;i < buffer.capacity(); i++){
            buffer.writeByte(i);
        }
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
    }
}
