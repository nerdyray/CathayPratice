package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//**
// CustomerResponse使用泛型，方便接下來重用
//  */
@Data
public class CustomerResponse<T> {
    // PrHeader
    @JsonProperty("PRHEADER")
    private PrHeader prHeader;
    // 系統回應
    @JsonProperty("TRANRS")
    private T tranrs;

}
