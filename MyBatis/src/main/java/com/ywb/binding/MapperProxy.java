package com.ywb.binding;

import com.ywb.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */
public class MapperProxy<T> implements Serializable, InvocationHandler {


    private static final long serialVersionUID = -9222833116332052894L;

    private final Class<T> mapperInterface;

    private final SqlSession sqlSession;

    public MapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        return "你被代理了" + sqlSession.getMapper(mapperInterface);
    }
}
