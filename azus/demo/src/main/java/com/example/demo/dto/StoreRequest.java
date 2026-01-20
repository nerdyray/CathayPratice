package com.example.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StoreRequest<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("MWHEADER")
    private MwHeader mwheader;
    @JsonProperty("TRANRQ")
    private T tranrq;
}
