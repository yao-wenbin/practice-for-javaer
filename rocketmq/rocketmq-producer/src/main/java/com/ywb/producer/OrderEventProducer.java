package com.ywb.producer;

import com.ywb.message.OrderCreateMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer {

    private static final String TOPIC_NAME = "application";

    private final RocketMQTemplate mq;

    public void send(OrderCreateMessage message) {
        mq.send(TOPIC_NAME, MessageBuilder.withPayload(message).build());
        log.info("Send Success！");
    }

}
