package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProduct {
    private Long id;
    private Product product;
    private BigDecimal price;
    private Integer quantity;
}
