package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 T001Tranrs 物件代表「更新店家」交易成功後的回應資料。
 * 通常更新類型的交易如果成功，回應的資料本體 (tranrs) 可以是空的，
 * 主要透過 MwHeader 中的 returnCode 來判斷是否成功。
 * implements Serializable: 表示這個物件可以被序列化。
 */
public class T001Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 對應 JSON 的 "TRANRS"
    // 這裡的 tranrs 是一個 TRANRS 型別的物件，但 TRANRS 類別本身是空的，
    // 所以這個欄位在 JSON 中會是一個空物件 {}。
    @JsonProperty("TRANRS")
    private TRANRS tranrs;
}
