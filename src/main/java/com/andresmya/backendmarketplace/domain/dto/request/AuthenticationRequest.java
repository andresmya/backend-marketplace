package com.andresmya.backendmarketplace.domain.dto.request;

import com.andresmya.backendmarketplace.persistence.entity.CategoryEntity;
import lombok.Data;
import org.mapstruct.Mapper;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
