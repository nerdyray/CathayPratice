package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable

public class CarPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "MANUFACTURER")
    private String manufacturer;

    @Column(name = "TYPE")
    private String type;

    public CarPK() {
    }
}
