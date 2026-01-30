package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 Q001TranrqPage 物件專門用來存放分頁查詢時需要的頁碼和每頁筆數。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class Q001TranrqPage implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // @NotNull: 驗證欄位，確保傳入的頁碼不能是 null。
    @NotNull
    private Integer pageNumber; // 當前頁碼 (通常從 0 開始)

    // @NotNull: 驗證欄位，確保傳入的每頁筆數不能是 null。
    @NotNull
    private Integer pageSize; // 每頁顯示的資料筆數
}