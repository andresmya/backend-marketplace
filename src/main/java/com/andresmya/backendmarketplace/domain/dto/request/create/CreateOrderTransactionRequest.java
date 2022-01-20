package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

@Data
public class CreateOrderTransactionRequest {
    private Long orderId;
    private Integer orderStatusId;
}
