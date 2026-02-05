package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import lombok.Data;

/**
 * T002 交易的請求資料傳輸物件 (DTO)。
 * 用於客戶資料更新交易，包含要更新的客戶資料。
 */
@Data
public class T002Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 包含要更新的客戶詳細資料。
     * 會對此物件進行遞迴驗證。
     */
    @Valid
    @JsonProperty("DATA")
    private TranData data;
}
