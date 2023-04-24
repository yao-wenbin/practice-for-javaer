package com.ywb.controller;

import cn.hutool.json.JSONObject;
import com.ywb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yaowenbin
 * @Date 2022/10/9
 */

@RestController
@Slf4j
public class TestController {

    @GetMapping("test")
    public JSONObject testApi() {
        JSONObject json = new JSONObject();
        json.putIfAbsent("code", 200);
        json.putIfAbsent("data", new JSONObject().putOnce("username", "zhangsan").putOnce("age", 123));
        return json;
    }

    @GetMapping("test-path/{path}")
    public JSONObject testApi(@PathVariable String path) {
        JSONObject data = new JSONObject().putOnce("username", "zhangsan").putOnce("age", 123);
        data.putIfAbsent("path" ,path);

        JSONObject json = new JSONObject();
        json.putIfAbsent("code", 200);
        json.putIfAbsent("data", data);
        return json;
    }

    @PostMapping("form-data")
    public JSONObject testApi(@RequestBody  User user) {
        log.info("{}", user);
        JSONObject json = new JSONObject();
        json.putIfAbsent("code", 200);
        json.putIfAbsent("data", user);
        return json;
    }
}
