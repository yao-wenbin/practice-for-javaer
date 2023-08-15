package io.github.yaowenbin.netty.reactormodel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author yaowenbin
 * @Date 2023/7/31
 */
public class SingleThreadReactorExmaple {

    public static void main(String[] args) {
        // EventLoopGroup(int) 表示EventLoop中的线程数，单线程传入1即可
        var g = new NioEventLoopGroup(1);
        var b = new ServerBootstrap();
        b.group(g);
    }

}
