package com.ywb.binding;

import com.ywb.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.sql.SQLSyntaxErrorException;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */

public class MapperProxyFactory<T> {
    private final  Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
