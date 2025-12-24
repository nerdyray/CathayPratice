package com.example.pratice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SPRING_CUSTOMER")

public class CustomerEntity {

    @Id
    @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
    private Long custmoter_ID;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "BIRTHDAY")
    private Integer birthday;

    @Column(name = "SEX", length = 1)
    private String sex;

    @Column(name = "ID", length = 20)
    private String id;
}
