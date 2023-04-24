package com.ywb.edd;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @Author yaowenbin
 * @Date 2022/9/21
 */
public class TestEvent extends ApplicationEvent {
    @Getter
    private final String name;

    public TestEvent(Object source, String name) {
        super(source);
        this.name = name;
    }
}
