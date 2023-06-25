package com.ywb.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;

/**
 * @Author yaowenbin
 * @Date 2023/5/22
 */
@SpringBootApplication
public class StreamRocketMQApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication.run(StreamRocketMQApplication.class, args);
    }

    @Autowired
    StreamBridge streamBridge;


    @Override
    public void run(String... args) throws Exception {
        streamBridge.send("charon-out-0", MessageBuilder.withPayload("message").build());

    }

    @Bean
    public Consumer<String> input() {
        return s -> {
            System.out.println(s);
        };
    }

}
