package io.github.yaowenbin.netty.chapter2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Author yaowenbin
 * @Date 2023/7/20
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");;
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }

    public void start() throws Exception {
        EchoClientHandler clientHandler = new EchoClientHandler();
        EventLoopGroup g = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(g).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(clientHandler);
                }
            });
            ChannelFuture f = b.connect().sync();

            f.channel().closeFuture().sync();
        } finally {
            g.shutdownGracefully().sync();
        }
    }

    @ChannelHandler.Sharable
    static class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        /**
         * 当客户端和服务端建立连接时
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            // 当连接打开时发送消息
            ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", Charset.defaultCharset()));
        }

        /**
         * 当读取到服务端返回的消息时
         */
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
            System.out.println("Client received: " + in.toString(Charset.defaultCharset()));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }

    }


}
