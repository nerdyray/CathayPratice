package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Q001Tranrq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "姓名不可為空")
    @Size(min = 1, max = 20)
    private String storeName;
    @NotNull
    private Q001TranrqPage page;
}
