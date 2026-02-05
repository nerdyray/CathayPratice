package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Q002 交易的回應資料傳輸物件 (DTO)。
 * 包含分頁查詢的結果，包括客戶列表、分頁統計和排序資訊。
 */
@Data
public class Q002Tranrs implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 每頁的資料筆數。
     */
    @JsonProperty("pageSize")
    private int pageSize;

    /**
     * 當前回應的頁碼。
     */
    @JsonProperty("pageNumber")
    private int pageNumber;

    /**
     * 總頁數。
     */
    @JsonProperty("totalPage")
    private int totalPage;

    /**
     * 符合查詢條件的總資料筆數。
     */
    @JsonProperty("totalCount")
    private Long totalCount;

    /**
     * 當前頁的客戶資料項目列表。
     */
    @JsonProperty("items")
    private List<Q002TranrsItems> items;

    /**
     * 回應中使用的排序資訊。
     */
    @JsonProperty("SORTINFO")
    private Q002TranrqSortInfo SortInfo;
}
