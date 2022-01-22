package com.andresmya.backendmarketplace.domain;

import lombok.Data;

@Data
public class CustomerAddress {
    private Integer id;
    private Integer customerId;
    private String address;
    private String zipCode;
    private City city;
}
