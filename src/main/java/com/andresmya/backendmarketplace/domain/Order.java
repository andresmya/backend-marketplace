package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private Integer customerId;
    private CustomerAddress customerAddress;
    private Date createdAt;
}
