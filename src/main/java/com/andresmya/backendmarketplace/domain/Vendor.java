package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Vendor {
    private Integer id;
    private String name;
    private Date updated_at;
    private Date created_at;
}
