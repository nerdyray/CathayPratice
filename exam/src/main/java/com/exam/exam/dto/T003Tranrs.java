package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * T003 交易的回應資料傳輸物件 (DTO)。
 * 用於表示客戶資料刪除交易的結果。
 */
@Data
public class T003Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

}
