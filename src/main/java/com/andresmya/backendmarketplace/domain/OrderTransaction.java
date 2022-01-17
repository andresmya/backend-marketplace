package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderTransaction {
    private Long id;
    private OrderStatus orderStatus;
    private Date created_at;
}
