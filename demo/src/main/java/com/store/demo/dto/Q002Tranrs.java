package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 Q002Tranrs 物件代表某個查詢交易的回應資料。
 * 結構與 Q001Tranrs 非常相似，都包含了分頁資訊和項目列表。
 * implements Serializable: 表示這個物件可以被序列化。
 */
public class Q002Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 對應 JSON 的 "pageSize"
    @JsonProperty("pageSize")
    private int pageSize;

    // 對應 JSON 的 "pageNumber"
    @JsonProperty("pageNumber")
    private int pageNumber;

    // 對應 JSON 的 "totalPage"
    @JsonProperty("totalPage")
    private int totalPage;

    // 對應 JSON 的 "totalCount"
    @JsonProperty("totalCount")
    private Long totalCount;

    // 對應 JSON 的 "items"，存放 Q001TranrsItems 物件列表
    @JsonProperty("items")
    private List<Q001TranrsItems> items;
}
