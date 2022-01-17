package com.andresmya.backendmarketplace.domain;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String name;
    private List<Privilege> privileges;
}
