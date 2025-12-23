package com.example.demo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CarResponse {

    @JsonProperty
    private String manufacturer;
    @JsonProperty
    private String type;
    @JsonProperty
    private BigDecimal minPrice;
    @JsonProperty
    private BigDecimal price;
}
