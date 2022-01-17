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
    private String vendor;
    private Date updated_at;
    private Date created_at;
}
