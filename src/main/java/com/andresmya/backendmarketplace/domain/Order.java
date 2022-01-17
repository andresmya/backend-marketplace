package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Integer customerId;
    private List<OrderProduct> orderProducts;
    private CustomerAddress customerAddress;
    private Date created_at;
}
