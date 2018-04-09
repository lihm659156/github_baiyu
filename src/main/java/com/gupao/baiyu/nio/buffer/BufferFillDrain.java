package com.gupao.baiyu.nio.buffer;

import java.nio.CharBuffer;

public class BufferFillDrain {

    private static int index = 0;

    private static String[] strings = {
        "A random string value",
        "The product of an infinite number of mankeys",
        "Hey hey we're the Monkees",
        "Opening act for the Monkees: Jimi Hendrix",
        "'Scuse me while I kiss this fly",
        "Help Me! Help Me!"
    };

    public static void main(String[] args) {
        //分配缓冲区
        CharBuffer buffer = CharBuffer.allocate(100);
        while(fillBuffer(buffer)){
            //将limit=position;然后position=0
            buffer.flip();
            drainBuffer(buffer);
            //position=0;limit=capacity
            buffer.clear();
        }

    }

    private static void drainBuffer(CharBuffer buffer){
        //position和limit位置之间是否有数据
        while(buffer.hasRemaining()){
            // 读取数据，然后position增加位置
            System.out.print(buffer.get());
        }
        System.out.println("");
    }

    private static boolean fillBuffer(CharBuffer buffer){
        if(index >= strings.length){
            return false;
        }

        String string = strings[index++];

        for(int i=0;i<string.length();i++){
            buffer.put(string.charAt(i));
        }

        return true;
    }





















}
