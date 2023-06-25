package com.yaowb.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2023/6/13
 */
@Slf4j
public class ConsumerApplication {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DefaultConsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("demo", "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            log.info("message received: {}", consumeConcurrentlyContext);

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();

        DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer("DefaultConsumer2");
        consumer2.setNamesrvAddr("127.0.0.1:9876");
        consumer2.subscribe("demo2", "*");

        consumer2.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            log.info("message received: {}", consumeConcurrentlyContext);

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer2.start();

    }

}
