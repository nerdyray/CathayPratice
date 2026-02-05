package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 通用的伺服器端回應資料傳輸物件 (DTO)。
 * 這是一個泛型類別，用於封裝所有對客戶端的回應。
 *
 * @param <T> 交易回應 (TRANRS) 的具體資料類型。
 */
@Data
public class CustomerResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 訊息標頭 (Message Header)，包含每次回應的元數據，如返回碼和返回訊息。
     */
    @JsonProperty("MWHEADER")
    private MWHEADER mwheader;

    /**
     * 交易回應本體 (Transaction Response Body)。
     * 這是泛型部分，會根據不同的交易結果而有不同的具體類別。
     */
    @JsonProperty("TRANRS")
    private T tranrs;
}
