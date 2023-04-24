package com.ywb.consumer;

import com.ywb.consumer.message.OrderCreateMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 * 以推模式接收时间
 */
@RocketMQMessageListener(topic = "application", consumerGroup = "consumers")
@Slf4j
@Service
public class OrderEventPushConsumer implements RocketMQListener<OrderCreateMessage> {

    @Override
    public void onMessage(OrderCreateMessage orderCreateMessage) {
        log.info("get message success !: {}", orderCreateMessage);
    }

}
