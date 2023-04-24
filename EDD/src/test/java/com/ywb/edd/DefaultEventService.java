package com.ywb.edd;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author yaowenbin
 * @Date 2022/9/21
 */

@Service
public class DefaultEventService {
    @Resource
    ApplicationEventPublisher publisher;
    public void publishEvent() {

        publisher.publishEvent(new TestEvent(this, "testEvent"));

    }
}
