package com.ywb.edd;

import con.ywb.edd.MainApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * @Author yaowenbin
 * @Date 2022/9/21
 */

@ExtendWith(SpringExtension.class)
public class EventTests {

    DefaultEventService eventService = new DefaultEventService();

    @Test
    public void eventTest() {
        eventService.publishEvent();
    }
}
