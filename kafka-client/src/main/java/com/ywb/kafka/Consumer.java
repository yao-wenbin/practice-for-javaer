package com.ywb.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @Author yaowenbin
 * @Date 2022/7/3
 */
public class Consumer {
    public static void main(String[] args) {
        ProducerRecord<String, String> record = new ProducerRecord<>("CustomerCountry", "Precision Products", "France");
    }
}
