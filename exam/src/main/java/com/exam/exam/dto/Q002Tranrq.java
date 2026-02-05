package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Q002 交易的請求資料傳輸物件 (DTO)。
 * 用於分頁查詢客戶資料，可包含過濾條件和排序資訊。
 */
@Data
public class Q002Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分頁資訊，包含頁碼和每頁筆數。
     */
    @JsonProperty("PAGE")
    private Q002TranrqPage page;

    /**
     * 查詢條件資料。
     * 可用於根據客戶的某些屬性進行過濾。
     */
    @JsonProperty("DATA")
    private TranData tranrqData;

    /**
     * 排序資訊，包含排序欄位和排序方向。
     */
    @JsonProperty("STOREINFO")
    private Q002TranrqSortInfo storeInfo;

}
