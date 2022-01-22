package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderTransactionRequest {
    private Integer orderStatusId;
}
