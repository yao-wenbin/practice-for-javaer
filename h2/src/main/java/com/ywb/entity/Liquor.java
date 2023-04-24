package com.ywb.entity;

/**
 * @Author yaowenbin
 * @Date 2022/7/12
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 酒
 */
@Data
public class Liquor {
    @TableId(type = IdType.ASSIGN_ID)
    public Long id;
    public String name;
    public Integer price;
}

