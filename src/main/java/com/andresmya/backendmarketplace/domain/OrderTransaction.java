package com.andresmya.backendmarketplace.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderTransaction {
    private Long id;
    private Long orderId;
    private OrderStatus orderStatus;
    private Date createdAt;

    public OrderTransaction(Long orderId, OrderStatus orderStatus){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
