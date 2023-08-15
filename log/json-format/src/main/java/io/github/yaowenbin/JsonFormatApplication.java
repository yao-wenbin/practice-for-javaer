package io.github.yaowenbin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author yaowenbin
 * @Date 2023/8/13
 */
@SpringBootApplication
@Slf4j
public class JsonFormatApplication {
    public static void main(String... args) {
        SpringApplication.run(JsonFormatApplication.class, args);
        log.error("you can just goooood");
    }
}
