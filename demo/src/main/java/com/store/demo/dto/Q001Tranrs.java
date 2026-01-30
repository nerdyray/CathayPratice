package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 Q001Tranrs 物件代表「查詢店家」交易成功後，回傳給前端的資料結構。
 * 包含了分頁資訊和查詢到的店家列表。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class Q001Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 對應 JSON 的 "pageSize"
    @JsonProperty("pageSize")
    private int pageSize; // 每頁筆數

    // 對應 JSON 的 "pageNumber"
    @JsonProperty("pageNumber")
    private int pageNumber; // 當前頁碼

    // 對應 JSON 的 "totalPage"
    @JsonProperty("totalPage")
    private int totalPage; // 總頁數

    // 對應 JSON 的 "totalCount"
    @JsonProperty("totalCount")
    private Long totalCount; // 總資料筆數

    // 對應 JSON 的 "items"
    // 這是一個列表，存放所有查詢到的店家資料 (Q001TranrsItems 物件)
    @JsonProperty("items")
    private List<Q001TranrsItems> items;
}
