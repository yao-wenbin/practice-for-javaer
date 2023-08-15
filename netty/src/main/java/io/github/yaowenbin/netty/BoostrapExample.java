package io.github.yaowenbin.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;


/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
public class BoostrapExample {

    public static void main(String[] args) {
        NioEventLoopGroup g = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap()
                .group(g)
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Receive Data");
                    }
                });
        ChannelFuture future = b.connect(new InetSocketAddress("www.baidu.com", 80));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("connect success");
            } else {
                System.err.println("connect failure");
                channelFuture.cause().printStackTrace();
            }
        });

    }
}
