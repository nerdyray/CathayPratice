package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * T001 交易的回應資料傳輸物件 (DTO)。
 * 用於表示客戶資料新增交易的結果。
 */
@Data
public class T001Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 交易回應本體。
     * 在此情境下，它是一個空的標記物件。
     */
    @JsonProperty("TRANRS")
    private TRANRS tranrs;

}
