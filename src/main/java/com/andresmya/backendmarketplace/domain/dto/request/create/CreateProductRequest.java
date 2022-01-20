package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer categoryId;
    private Integer vendorId;
}
