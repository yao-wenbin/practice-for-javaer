package com.ywb.session;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */
public interface SqlSession {
    <T> T selectOne(String statement, Object parameter);

    <T> T getMapper(Class<T> type);
}
