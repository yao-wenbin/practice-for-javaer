package com.ywb.producer;

import com.ywb.message.OrderPaidMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderPaidEventProducer {

    private final RocketMQTemplate template;


    public void send(OrderPaidMessage message) {
        TransactionSendResult result = template.sendMessageInTransaction("application-transaction",
                MessageBuilder.withPayload(message).build(),
                null
        );

        log.info("Transaction Send Result: {}", result);
    }

    @RocketMQTransactionListener
    static class TransactionListenerImpl implements RocketMQLocalTransactionListener {

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
            return null;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
            OrderPaidMessage payload = (OrderPaidMessage) message.getPayload();
            return checkOrderStatus(payload.getOrderId()) ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
        }

        public boolean checkOrderStatus(Long id) {
            return id != null;
        }
    }

}
