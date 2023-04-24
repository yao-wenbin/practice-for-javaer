package com.ywb.remotedebug;

/**
 * @Author yaowenbin
 * @Date 2022/9/21
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--------start--------");

        if (args != null && args.length != 0) {
            for (int order = 1; order <= args.length; ++order) {
                System.out.println(order + "：" + args[order - 1]);
            }
        } else {
            System.out.println("Hello world.");
        }

        for (int order = 0; true; ++order) { // 无限循环延时代码
            System.out.println(order);
            Thread.sleep(1000); // 休眠 1 秒
        }
    }
}
