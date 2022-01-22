package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Integer customerAddressId;
    private List<ProductQuantity> products;
    @Data
    public static class ProductQuantity {
        private Long productId;
        private Integer quantity;
    }
}
