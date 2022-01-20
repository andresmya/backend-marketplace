package com.andresmya.backendmarketplace.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(insertable = false, updatable = false)
    private Date updatedAt;
    @Column(insertable = false, updatable = false)
    private Date createdAt;
}
