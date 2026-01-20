package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class StoreResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("TRANRS")
    private T tranrs;

}
