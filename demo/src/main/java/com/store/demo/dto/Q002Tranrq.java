package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class Q002Tranrq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer storeId;
}
