package com.ywb.edd;


import org.springframework.context.event.EventListener;

/**
 * @Author yaowenbin
 * @Date 2022/9/21
 */
public class TestEventListener {

    @EventListener({TestEvent.class})
    public void eventTest(TestEvent event) {
        System.out.println(event.getName());
    }

}
