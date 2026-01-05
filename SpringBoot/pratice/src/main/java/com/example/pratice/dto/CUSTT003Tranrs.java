package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 更新資料的回應
 */
@Data
public class CUSTT003Tranrs {
    @JsonProperty("MESSAGE")
    private String message;
}
