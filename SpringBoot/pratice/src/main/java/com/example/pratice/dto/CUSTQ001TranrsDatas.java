package com.example.pratice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CUSTQ001TranrsDatas {

    @JsonProperty("CUSTOMERId")
    private Long customerId;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("BIRTHDAY")
    private LocalDate birthday;
    @JsonProperty("SEX")
    private String sex;
    @JsonProperty("ID")
    private String id;

}
