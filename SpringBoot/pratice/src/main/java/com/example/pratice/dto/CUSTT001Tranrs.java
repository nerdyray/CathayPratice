package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 查詢客戶的回應資料
 */
@Data
public class CUSTT001Tranrs {

    @JsonProperty("MESSAGE")
    private String message;
}
