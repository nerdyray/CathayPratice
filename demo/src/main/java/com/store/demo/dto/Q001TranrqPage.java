package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Q001TranrqPage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
}
