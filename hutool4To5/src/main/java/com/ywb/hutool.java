package com.ywb;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2022/7/17
 */
public class hutool {
    public static void main(String[] args) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("user", null);

        Object user = Obj.getOrDefault(obj.get("user"), 2L);
        System.out.println(user);

        obj.put("username", Obj.getOrDefault(obj.get("user"), "yaowb"));
        System.out.println(obj.get("username"));
    }
}
