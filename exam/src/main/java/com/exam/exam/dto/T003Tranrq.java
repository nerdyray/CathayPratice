package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Digits;
import lombok.Data;

/**
 * T003 交易的請求資料傳輸物件 (DTO)。
 * 用於客戶資料刪除交易，包含要刪除的客戶訂單 ID。
 */
@Data
public class T003Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 要刪除的客戶訂單 ID。
     * 限制為最多 999 的整數。
     */
    @Digits(integer = 999, fraction = 0)
    @JsonProperty("orderId")
    private Integer orderId;
}
