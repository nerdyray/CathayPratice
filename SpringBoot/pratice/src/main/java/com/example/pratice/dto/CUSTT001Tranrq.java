package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CUSTT001Tranrq {

    @Min(13)
    @JsonProperty("CustomerId")
    private Long customerId;
    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Birthday")
    private Integer birthday;
    @Pattern(regexp = "^(f|m)$", message = "性別只能是 f 或 m")
    @JsonProperty("Sex")
    private String sex;
    @JsonProperty("Id")
    private String id;
}
