package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * Q002 交易請求中排序資訊的資料傳輸物件 (DTO)。
 */
@Data
public class Q002TranrqSortInfo implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 排序方向（例如 "ASC" 或 "DESC"）。
     */
    private String sortBy;

    /**
     * 要排序的欄位名稱。
     */
    private String sortColumn;

}