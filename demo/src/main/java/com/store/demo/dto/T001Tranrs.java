package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class T001Tranrs implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("TRANRS")
    private TRANRS tranrs;
}
