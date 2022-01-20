package com.andresmya.backendmarketplace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;
    private Role role;
    private Date updated_at;
    private Date created_at;
}
