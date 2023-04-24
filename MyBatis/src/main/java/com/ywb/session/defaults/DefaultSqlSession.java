package com.ywb.session.defaults;

import com.ywb.binding.MapperRegistry;
import com.ywb.session.SqlSession;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + "参数：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }

    public static SqlSession openSession(MapperRegistry mapperRegistry) {
        return new DefaultSqlSession(mapperRegistry);
    }
}
