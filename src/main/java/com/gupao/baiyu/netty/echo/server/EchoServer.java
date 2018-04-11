package com.gupao.baiyu.netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 1){
//            System.err.println("Usage : " + EchoServer.class.getSimpleName()+"<port>");
//            return;
//        }
//        int port = Integer.parseInt(args[0]);
        int port = 9999;
        new EchoServer(port).start();
    }

    public void start() throws Exception{
        // 创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            // 创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 指定使用NIO的传输Channel
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 添加EchoServerHandler到Channel的ChannelPipeline
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            // sync等待服务器关闭
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + "started and listen on " + f.channel().localAddress());
            // 关闭channel和块
            f.channel().closeFuture().sync();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            // 关闭EventLoopGroup，释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
