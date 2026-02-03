package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Q002Tranrs implements Serializable {
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
    private List<Q002TranrsItems> items;
    @JsonProperty("SORTINFO")
    private Q002TranrqSortInfo SortInfo;
}
