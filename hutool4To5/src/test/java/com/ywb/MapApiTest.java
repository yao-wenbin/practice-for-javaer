package com.ywb;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yaowenbin
 * @Date 2023/6/9
 */
class MapApiTest {

    @Test
    void computeIfAbsent() {
        Map<String, String> map = new HashMap<>();
        String result = map.computeIfAbsent("first", k -> "yes");

        assertThat(result).isEqualTo("yes");
        assertThat(map.get("first")).isEqualTo("yes");
    }

    @Test
    void getOrDefault() {
        Map<String, String> map = new HashMap<>();
        String result = map.getOrDefault("first", "yes");

        assertThat(result).isEqualTo("yes");
        assertThat(map.get("first")).isNull();
    }

    @Test
    void computIfAbsent() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("AaAa", key->map.computeIfAbsent("BBBB",key2->"42"));
       //  ConcurrentHashMapUtils.computeIfAbsent(map, "AaAa", key -> map.computeIfAbsent("BBBB", key2 -> "42"));

    }

}