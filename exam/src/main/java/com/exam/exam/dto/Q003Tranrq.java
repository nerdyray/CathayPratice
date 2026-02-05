package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Q003 交易的請求資料傳輸物件 (DTO)。
 * 用於根據身分證字號檢查客戶是否存在。
 */
@Data
public class Q003Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 要檢查的身分證字號。
     * 此欄位為必填，且長度上限為 20。
     */
    @NotBlank(message = "身分證字號為必填")
    @Size(max = 20)
    @JsonProperty("idNum")
    private String idNum;

}
