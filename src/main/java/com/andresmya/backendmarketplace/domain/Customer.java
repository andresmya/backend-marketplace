package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private User user;
    private Date updatedAt;
    private Date createdAt;
}
