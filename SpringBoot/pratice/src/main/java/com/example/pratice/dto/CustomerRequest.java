package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequest {

    @Min(13)
    @JsonProperty("Custmoter_id")
    private Long custmoter_ID;

    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Birthday")
    private Integer birthday;

    @JsonProperty("Sex")
    private String sex;

    @JsonProperty("Id")
    private String id;
}
