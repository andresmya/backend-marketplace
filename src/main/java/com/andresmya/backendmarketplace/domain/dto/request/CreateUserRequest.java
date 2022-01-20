package com.andresmya.backendmarketplace.domain.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private Integer rolId;
}
