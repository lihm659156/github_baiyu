package com.gupao.baiyu.nio.channel.socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ChannelAccept {

    public static final String GREETING = "Hello I must be going,\r\n";

    public static void main(String[] args) throws Exception {
        int port = 1234;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        // 装饰，byte缓冲区
        ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());

        // 创建通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 绑定端口，监听连接
        ssc.socket().bind(new InetSocketAddress(port));

        // 非阻塞
        ssc.configureBlocking(false);

        while(true){
            System.out.println("Waiting for connections");
            // 监听新进来的连接,它返回一个包含新进来的连接的 SocketChannel
            // ServerSocket.accept          阻塞
            // ServerSocketChannel.accept   非阻塞
            SocketChannel sc = ssc.accept();
            if(sc == null){
                Thread.sleep(2000);
            }else{
                System.out.println("Incoming connection from : "+sc.socket().getRemoteSocketAddress());
                // position=0
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }


    }


}






















