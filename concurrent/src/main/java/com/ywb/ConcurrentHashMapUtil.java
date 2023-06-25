package com.ywb;

import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * @Author yaowenbin
 * @Date 2023/6/25
 */
public class ConcurrentHashMapUtil {

    private static boolean isJdk8;
    static {
        // Java 8
        // Java 9+: 9,11,17
        try {
            isJdk8 = System.getProperty("java.version").startsWith("1.8.");
        } catch (Exception ignore) {
            isJdk8 = true;
        }
    }


    public static <K, V> V computeIfAbsent(ConcurrentMap<K, V> map, K key, Function<? super K, ? extends V> func) {
        Objects.requireNonNull(func);
        if (isJdk8) {
            V v = map.get(key);
            if (null == v) {
                v = map.computeIfAbsent(key, func);
                // this bug fix methods maybe cause `func.apply` multiple calls.
                v = func.apply(key);
                if (null == v) {
                    return null;
                }
                final V res = map.putIfAbsent(key, v);
                if (null != res) {
                    // if pre value present, means other thread put value already, and putIfAbsent not effect
                    // return exist value
                    return res;
                }
            }
            return v;
        } else {
            return map.computeIfAbsent(key, func);
        }

    }
}
