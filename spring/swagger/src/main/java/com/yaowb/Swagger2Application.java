package com.yaowb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author yaowenbin
 * @Date 2023/6/12
 */
@SpringBootApplication
@EnableSwagger2
public class Swagger2Application {
    public static void main(String... args) {
        SpringApplication.run(Swagger2Application.class, args);
    }
}
