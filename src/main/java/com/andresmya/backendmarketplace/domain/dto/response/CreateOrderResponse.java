package com.andresmya.backendmarketplace.domain.dto.response;

import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.domain.OrderProduct;
import com.andresmya.backendmarketplace.domain.OrderTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderResponse {
    private Long orderId;
    private CustomerAddress customerAddress;
    private List<OrderProduct> products;
    private OrderTransaction transaction;
}
