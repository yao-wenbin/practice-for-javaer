package com.ywb.binding;

import cn.hutool.core.lang.ClassScanner;
import com.ywb.session.SqlSession;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<?> mapperFactory = knownMappers.get(type);
        if (mapperFactory == null) {
            throw new RuntimeException("Type " + type + "is not known to the MapperRegistry");
        }

        try {
            return (T) mapperFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause:" + e, e);
        }
    }

    public void addMapper(Class<?> type) {
        if(!type.isInterface()) {
            return ;
        }
        if(hasMapper(type)) {
            throw new RuntimeException("mapper already register");
        }
        knownMappers.put(type, new MapperProxyFactory<>(type));
    }

    public void scanMappers(String packageName) {
        Set<Class<?>> classes = ClassScanner.scanPackage(packageName);
        for (Class<?> type : classes) {
            addMapper(type);
        }
    }

    private boolean hasMapper(Class<?> type) {
        return knownMappers.get(type) != null;
    }


}
