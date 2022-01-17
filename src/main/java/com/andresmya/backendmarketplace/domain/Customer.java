package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Integer id;
    private String first_name;
    private String last_name;
    private Date updated_at;
    private Date created_at;
}
