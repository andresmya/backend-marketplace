package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Category category;
    private Vendor vendor;
    private Date updatedAt;
    private Date createdAt;
}
