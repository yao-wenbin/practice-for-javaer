package com.ywb.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author yaowenbin
 * @Date 2022/10/9
 */
@Data
@Accessors(chain = true)
public class User {
    private Long uid;
    private String username;
}
