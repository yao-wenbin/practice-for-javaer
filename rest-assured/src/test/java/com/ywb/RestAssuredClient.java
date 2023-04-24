package com.ywb;

import com.ywb.component.ComponentInject;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

/**
 * @Author yaowenbin
 * @Date 2022/10/9
 */
@Component
public class RestAssuredClient {

    public static int port;

    public static ComponentInject componentInject;
    @Value("${server.port}")
    public void setPort(int serverPort) {
        RestAssuredClient.port = serverPort;
    }

    @Autowired
    public void setComponentInject(ComponentInject componentInject) {
        RestAssuredClient.componentInject = componentInject;
    }

    public static RequestSpecification given() {
        return RestAssured.given().port(port).log().all();
    }

    public static RequestSpecification login() {
        String token = "xxxxx";
        return RestAssured.given().port(port).log().all().header("Authorization", token);
    }




}
