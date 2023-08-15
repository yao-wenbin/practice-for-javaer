package io.github.yaowenbin.netty.reactormodel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @Author yaowenbin
 * @Date 2023/7/31
 */
public class MultiThreadMasterSlaveExample {

    public static void main(String[] args) {
        var boss = new NioEventLoopGroup(1);
        var work = new NioEventLoopGroup();
        var b = new ServerBootstrap();
        b.group(boss, work);
    }

}
