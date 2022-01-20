package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
}
