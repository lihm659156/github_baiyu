package com.gupao.baiyu.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 2){
//            System.err.println("Usage : " + EchoClient.class.getName() + "<host><port>");
//            return;
//        }
//        final String host = args[0];
//        final int port = Integer.parseInt(args[1]);
        final String host = "localhost";
        final int port = 9999;
        new EchoClient(host,port).start();
    }

    public void start() throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
