package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

@Data
public class CustomerRequest<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("MWHEADER")
    private MWHEADER mwheader;
    @Valid
    @JsonProperty("TRANRQ")
    private T tranrq;
}
