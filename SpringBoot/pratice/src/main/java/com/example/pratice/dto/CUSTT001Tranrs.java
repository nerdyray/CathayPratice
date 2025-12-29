package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class CUSTT001Tranrs {

    @JsonProperty("MESSAGE")
    private String message;
}
