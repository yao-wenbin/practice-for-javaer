package com.ywb;

import com.ywb.model.User;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.*;
/**
 * @Author yaowenbin
 * @Date 2022/10/9
 */
@SpringBootTest(classes = RestAssuredApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension.class)
@Slf4j
public class Tests {

    @Test
    public void autoWiredWithStaticMember() {
        log.info("{}", RestAssuredClient.port);
        log.info("{}", RestAssuredClient.componentInject.number);
    }

    @Test
    public void validation() {
        RestAssuredClient.login().
                get("test").
        then().
                log().all().
                body("code", equalTo(200));
    }

    @Test
    public void ExtractDataAfterValidation() {
        Object path = RestAssuredClient.login().
                get("test").
        then().
            log().all().
            body("code", equalTo(200)).
        extract().
            path("data.username");
        log.info("result: {}", path);
    }

    @Test
    public void pathParamsInRequest_way1() {
        String path = "123";
        RestAssuredClient.login().pathParam("path", path).
        when().
                get("test-path/{path}").
        then().
                log().all().
                body("data.path", equalTo(path));
        log.info("result: {}", path);
    }

    @Test
    public void pathParamsInRequest_way2_recommended() {
        String path = "123";
        RestAssuredClient.login().
                when().
                get("test-path/{path}", path).
                then().
                log().all().
                body("data.path", equalTo(path));
    }

    @Test
    public void formDataInPostRequest() {
        User lisi = new User().setUid(1L).setUsername("lisi");

        RestAssuredClient.login().
            contentType(ContentType.JSON).
                body(lisi).
        when().
            post("form-data").
        then().
            log().all().
            body("data.username", equalTo("lisi")).
            body("data.uid", equalTo(1));
    }
}
