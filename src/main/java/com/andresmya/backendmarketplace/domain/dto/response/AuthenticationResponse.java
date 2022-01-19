package com.andresmya.backendmarketplace.domain.dto.response;

import com.andresmya.backendmarketplace.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String jwt;
    private Date expirationDate;
    private String username;
    private Role role;
}
