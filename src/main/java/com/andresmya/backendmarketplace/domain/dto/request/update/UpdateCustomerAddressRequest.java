package com.andresmya.backendmarketplace.domain.dto.request.update;

import lombok.Data;

@Data
public class UpdateCustomerAddressRequest {
    private String address;
    private String zipCode;
}
