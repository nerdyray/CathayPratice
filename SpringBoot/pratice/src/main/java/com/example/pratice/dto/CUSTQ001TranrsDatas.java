package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CUSTQ001TranrsDatas {
    @JsonProperty("CUSTOMERId")
    private Long custmoterId;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("BIRTHDAY")
    private Integer birthday;
    
    @JsonProperty("SEX")
    private String sex;
    @JsonProperty("ID")
    private String id;
    
}
