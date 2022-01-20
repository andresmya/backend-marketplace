package com.andresmya.backendmarketplace.domain.dto.request.update;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String email;
    private String recoveryCode;
}
