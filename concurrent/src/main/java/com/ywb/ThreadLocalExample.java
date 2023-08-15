package com.ywb;

/**
 * @Author yaowenbin
 * @Date 2023/7/26
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        final ThreadLocal local = new ThreadLocal();
        local.set(new Object());
    }
}
