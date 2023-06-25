package com.ywb.consumer.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 */

@Data
@Builder
public class Order {

    private Long orderId;

    private BigDecimal price;

    private Long productId;

    private Long productCount;

}
