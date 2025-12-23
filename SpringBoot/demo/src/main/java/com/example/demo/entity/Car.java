package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
// @IdClass(value = CarPK.class)
@Table(name = "CARS")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MIN_PRICE")
    private BigDecimal minPrice;

    @Column(name = "PRICE")
    private BigDecimal price;
}
