package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * Q002 交易請求中分頁資訊的資料傳輸物件 (DTO)。
 */
@Data
public class Q002TranrqPage implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 請求的頁碼（從 1 開始）。
     */
    private int pageNumber;

    /**
     * 每頁的資料筆數。
     */
    private int pageSize;
}
