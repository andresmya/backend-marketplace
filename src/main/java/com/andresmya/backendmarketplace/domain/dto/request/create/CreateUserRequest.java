package com.andresmya.backendmarketplace.domain.dto.request.create;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
    private Integer rolId;
}
