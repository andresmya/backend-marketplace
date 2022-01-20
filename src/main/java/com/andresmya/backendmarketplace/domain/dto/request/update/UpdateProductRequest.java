package com.andresmya.backendmarketplace.domain.dto.request.update;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String description;
    private BigDecimal price;
    private Integer stock;
}
