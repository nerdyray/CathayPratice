package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class StoreRequest<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Valid
    @JsonProperty("TRANRQ")
    // tranrq變數
    private T tranrq;
}
