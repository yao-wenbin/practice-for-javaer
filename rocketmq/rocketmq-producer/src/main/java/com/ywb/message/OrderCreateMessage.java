package com.ywb.message;

import cn.hutool.core.bean.BeanUtil;
import com.ywb.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.math.BigDecimal;

/**
 * @Author yaowenbin
 * @Date 2023/4/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateMessage {

    private Long orderId;

    private BigDecimal price;

    private Long productId;

    private Long productCount;

    public OrderCreateMessage(Order order) {
        BeanUtil.copyProperties(order, this);
    }

    public Message build() {
        return MessageBuilder.withPayload(this).build();
    }
}
