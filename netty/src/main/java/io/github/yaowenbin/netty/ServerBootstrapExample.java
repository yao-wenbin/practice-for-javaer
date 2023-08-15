package io.github.yaowenbin.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
public class ServerBootstrapExample {

    public static void main(String[] args) {
        EventLoopGroup g = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(g)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                        System.out.println("Receive Data" + byteBuf.toString(Charset.defaultCharset()));
                    }
                });
        ChannelFuture future = b.bind(new InetSocketAddress(8888));
        future.addListener((ChannelFutureListener) (channelFuture) -> {
           if (channelFuture.isSuccess()) {
               System.out.println("bind success");
           } else {
               System.err.println("bind failure");
               channelFuture.cause().printStackTrace();
           }
        });
    }

}
