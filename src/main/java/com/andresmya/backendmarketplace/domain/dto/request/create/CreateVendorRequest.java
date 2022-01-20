package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

@Data
public class CreateVendorRequest {
    private String name;
    private String email;
    private String password;
}
