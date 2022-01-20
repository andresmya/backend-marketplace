package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
