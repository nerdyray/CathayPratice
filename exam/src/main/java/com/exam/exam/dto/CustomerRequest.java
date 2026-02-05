package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

/**
 * 通用的客戶端請求資料傳輸物件 (DTO)。
 * 這是一個泛型類別，用於封裝所有來自客戶端的請求。
 *
 * @param <T> 交易請求 (TRANRQ) 的具體資料類型。
 */
@Data
public class CustomerRequest<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 訊息標頭 (Message Header)，包含每次請求的元數據。
     */
    @JsonProperty("MWHEADER")
    private MWHEADER mwheader;

    /**
     * 交易請求本體 (Transaction Request Body)。
     * 這是泛型部分，會根據不同的交易而有不同的具體類別。
     * {@code @Valid} 註解會觸發對其內部欄位的遞迴驗證。
     */
    @Valid
    @JsonProperty("TRANRQ")
    private T tranrq;
}
