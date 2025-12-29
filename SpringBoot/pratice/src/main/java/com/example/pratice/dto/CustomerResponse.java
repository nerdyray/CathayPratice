package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerResponse <T>  {

    @JsonProperty("PRHEADER")
    private PrHeader prHeader;
    @JsonProperty("TRANRS")
    private T tranrs;

}
