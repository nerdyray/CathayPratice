package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PrHeader {
@JsonProperty("SID")
private Long sid;

}
