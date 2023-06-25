package com.ywb.message;

import com.ywb.model.Order;
import lombok.Data;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 */
@Data
public class OrderPaidMessage {

    private Long orderId;

    public OrderPaidMessage(Order order) {
        this.orderId = order.getOrderId();
    }

}
