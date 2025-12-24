package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerRequest {
    @JsonProperty("Custmoter_id")
    private Long custmoter_ID;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Birthdat")
    private Integer birthday;

    @JsonProperty("Sex")
    private String sex;

    @JsonProperty("Id")
    private String id;
}
