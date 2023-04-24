package com.ywb;

import com.ywb.message.OrderCreateMessage;
import com.ywb.message.OrderPaidMessage;
import com.ywb.model.Order;
import com.ywb.producer.OrderEventProducer;
import com.ywb.producer.OrderPaidEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 */
@SpringBootApplication
@RequiredArgsConstructor
public class ProducerApplication implements CommandLineRunner {

    private final OrderEventProducer producer;

    private final OrderPaidEventProducer paidEventProducer;

    @Override
    public void run(String... args) {
        // Normal Message
        Order order = Order.builder().orderId(1L).price(BigDecimal.TEN).productCount(1L).productId(2L).build();
        OrderCreateMessage message = new OrderCreateMessage(order);
        producer.send(message);

        // Transaction Message
        OrderPaidMessage paidMessage = new OrderPaidMessage(order);
        paidEventProducer.send(paidMessage);
    }


    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }




}
