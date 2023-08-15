package io.github.yaowenbin.netty;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoop;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
public class EventLoopTest {

    Runnable task1 = () -> {
        try {
            System.out.println("task1 do something");
            Thread.sleep(2000);
            System.out.println("task1 success");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };

    Runnable task2 = () -> {
        try {
            System.out.println("task2 do something");
            Thread.sleep(2000);
            System.out.println("task2 success");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    };

    @Test
    void eventLoopWillExecOrderly() throws InterruptedException {
        EventLoop eventLoop = new DefaultEventLoop();

        eventLoop.schedule(task1, 1, TimeUnit.SECONDS);
        eventLoop.schedule(task2, 1, TimeUnit.SECONDS);

        Thread.sleep(5000);
    }

    @Test
    void butScheduleServiceWillExecConcurrently() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.schedule(task1, 1, TimeUnit.SECONDS);
        executor.schedule(task2, 1, TimeUnit.SECONDS);

        Thread.sleep(5000);
    }

}
