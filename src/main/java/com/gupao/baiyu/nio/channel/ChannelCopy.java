package com.gupao.baiyu.nio.channel;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelCopy {

    public static void main(String[] args) throws Exception{
        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);

//        channelCopy1(source,dest);

        channelCopy2(source,dest);

        source.close();
        dest.close();

    }

    private static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while(src.read(buffer) != -1){
            buffer.flip();
            dest.write(buffer);
            // 方法只会清除已经读过的数据（0到posstion）
            buffer.compact();
        }
        // limit=position;position=0;
        buffer.flip();
        // position,limit之间是否有数据
        while (buffer.hasRemaining()){
            dest.write(buffer);
        }
    }

    private static void channelCopy2(ReadableByteChannel src, WritableByteChannel dest) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while(src.read(buffer) != -1){
            buffer.flip();
            while(buffer.hasRemaining()){
                dest.write(buffer);
            }
            buffer.clear();
        }
    }

}

















