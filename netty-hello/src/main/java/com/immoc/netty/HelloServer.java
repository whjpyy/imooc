package com.immoc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现客户端发送一个请求，服务器会返回hello netty
 */
public class HelloServer {
    public static void main(String[] args) {

        // 定义一个线程组
        // 主线程租，用于接受客户端连接，但是不做任何处理，跟老板一样，不做事
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        // 从线程组，老板线程组会把任务丢给他，让手下线程组去做任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            // netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup) // 设置主从线程池
                    .channel(NioServerSocketChannel.class) // 设置nio的双向通道
                    .childHandler(new HelloServerInitializer()); // 子处理器，用户处理workerGroup

            // 启动Server，并且设置8088为启动的端口好，同事启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            // 监听关闭的channel，设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
