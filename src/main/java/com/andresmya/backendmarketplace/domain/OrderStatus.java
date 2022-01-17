package com.andresmya.backendmarketplace.domain;

import lombok.Data;

@Data
public class OrderStatus {
    private Integer id;
    private String name;
    private OrderStatusType orderStatusType;
}
