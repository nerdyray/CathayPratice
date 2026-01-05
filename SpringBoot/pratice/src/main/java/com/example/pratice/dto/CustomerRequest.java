package com.example.pratice.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Data;
//**
// CustomerRequest使用泛型，方便接下來重用
//  */

@Data
public class CustomerRequest<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Valid
    @JsonProperty("TRANRQ")
    // tranrq變數
    private T tranrq;

}
