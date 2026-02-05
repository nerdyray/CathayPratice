package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Data;

/**
 * T001 交易的請求資料傳輸物件 (DTO)。
 * 用於新增客戶資料。
 */
@Data
public class T001Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 包含要新增的客戶詳細資料。
     * 會對此物件進行遞迴驗證。
     */
    @Valid
    @JsonProperty("DATA")
    private TranData data;

}
