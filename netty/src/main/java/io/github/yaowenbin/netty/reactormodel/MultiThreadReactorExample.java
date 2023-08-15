package io.github.yaowenbin.netty.reactormodel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author yaowenbin
 * @Date 2023/7/31
 */
public class MultiThreadReactorExample {

    public static void main(String[] args) {
        // 为0或者是空时，会调用NettyRuntime.availableProcessors() * 2获得线程数，也就是CPU核心的两倍
        var g = new NioEventLoopGroup();
        var b = new ServerBootstrap();
        b.group(g);
    }

}
