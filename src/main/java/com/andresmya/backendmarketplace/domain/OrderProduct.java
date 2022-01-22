package com.andresmya.backendmarketplace.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderProduct {
    private Long id;
    private Long orderId;
    private Product product;
    private BigDecimal price;
    private Integer quantity;

    public OrderProduct (Product product, BigDecimal price, Integer quantity, Long orderId){
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

}
