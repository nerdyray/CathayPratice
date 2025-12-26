package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class CustomerResponse {

    @JsonProperty
    private Long custmoter_ID;
    @JsonProperty
    private String name;
    @JsonProperty
    private Integer birthday;
    @JsonProperty
    private String sex;
    @JsonProperty
    private String id;
}
