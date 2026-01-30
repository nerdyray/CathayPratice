package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這是一個通用的請求 (Request) 封裝物件。
 * 使用泛型 <T> 讓它可以接受不同型別的交易請求資料 (TRANRQ)。
 * 這樣可以標準化所有 API 的請求格式。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 * @param <T> 泛型參數，代表具體的交易請求資料型別，例如 Q001Tranrq 或 T002Tranrq。
 */
@Data
public class StoreRequest<T> implements Serializable {

    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 對應 JSON 的 "MWHEADER"
    // 固定的訊息標頭
    @JsonProperty("MWHEADER")
    private MwHeader mwheader;

    // 對應 JSON 的 "TRANRQ"
    // 泛型的交易請求資料本體
    @JsonProperty("TRANRQ")
    private T tranrq;
}