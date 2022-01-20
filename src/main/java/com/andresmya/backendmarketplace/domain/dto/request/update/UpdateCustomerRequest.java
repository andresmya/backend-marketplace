package com.andresmya.backendmarketplace.domain.dto.request.update;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private String first_name;
    private String last_name;
}
