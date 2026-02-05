package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Q001 交易的回應資料傳輸物件 (DTO)。
 * 包含根據訂單 ID 查詢到的客戶資料。
 */
@Data
public class Q001Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 包含客戶詳細資料的列表。
     * 通常情況下，這個列表只會有一個元素。
     */
    private List<TranData> tranData;
}
