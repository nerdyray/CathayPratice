package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class T003Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

}
