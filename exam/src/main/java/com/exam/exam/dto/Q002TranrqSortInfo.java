package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class Q002TranrqSortInfo implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;
    private String sortBy;
    private String Column;

}