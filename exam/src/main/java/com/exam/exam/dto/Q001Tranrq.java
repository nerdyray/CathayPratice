package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Digits;

import lombok.Data;

@Data
public class Q001Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;
    @Digits(integer = 999, fraction = 0)
    @JsonProperty("orderId")
    private Integer orderId;

}
