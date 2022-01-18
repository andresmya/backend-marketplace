package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private Role role;
    private Date updated_at;
    private Date created_at;
}
