package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

@Data
public class CreateCustomerAddressRequest {
    private Integer customerId;
    private String address;
    private String zipCode;
    private Integer cityId;
}
