package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class Q002TranrqPage implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    private int pageNumber;
    private int pageSize;
}
