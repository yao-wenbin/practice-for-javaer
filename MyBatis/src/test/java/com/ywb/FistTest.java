package com.ywb;

import com.ywb.binding.MapperRegistry;
import com.ywb.dao.IUserDao;
import com.ywb.session.SqlSession;
import com.ywb.session.defaults.DefaultSqlSession;
import org.junit.Test;


/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */

public class FistTest {

    @Test
    public void testMapperProxy() {
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.scanMappers("com.ywb.dao");

        SqlSession sqlSession = DefaultSqlSession.openSession(mapperRegistry);
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        String s = mapper.queryUserName("123123");
        System.out.println(s);
    }
}
