package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Digits;

import lombok.Data;

/**
 * Q001 交易的請求資料傳輸物件 (DTO)。
 * 用於根據訂單 ID 查詢單一客戶資料。
 */
@Data
public class Q001Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 要查詢的客戶訂單 ID。
     * 限制為最多 999 的整數。
     */
    @Digits(integer = 999, fraction = 0)
    @JsonProperty("orderId")
    private Integer orderId;

}
