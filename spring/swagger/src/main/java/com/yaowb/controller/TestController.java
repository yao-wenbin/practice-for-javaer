package com.yaowb.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yaowenbin
 * @Date 2023/6/12
 */
@Api("for test")
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "yes";
    }

}
