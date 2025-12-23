package com.example.demo.dto;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CarRequest {
    @JsonProperty("Manufactur")
    private String manufacturer;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Min_Price")
    private BigDecimal minPrice;
    @JsonProperty("Price")
    private BigDecimal price;
}
