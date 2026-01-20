package com.example.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StoreResponse <T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("MWHEADER")
    private MWHEADER mwheader;
    @JsonProperty("TRANRS")
    private T tranrs;
}
