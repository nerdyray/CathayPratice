package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * Q003 交易的回應資料傳輸物件 (DTO)。
 * 用於確認指定身分證字號的客戶是否存在。
 */
@Data
public class Q003Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 已確認存在的客戶身分證字號。
     */
    private String idNum;

}
