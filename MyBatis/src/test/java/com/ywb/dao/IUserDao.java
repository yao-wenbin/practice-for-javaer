package com.ywb.dao;

/**
 * @Author yaowenbin
 * @Date 2022/7/24
 */
public interface IUserDao {
    String queryUserName(String uid);

    String queryUserAge(String uid);
}
