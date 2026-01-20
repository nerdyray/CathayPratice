package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class XXACSTORET002Tranrs implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("TRANRS")
    private TRANRS tranrs;
}
