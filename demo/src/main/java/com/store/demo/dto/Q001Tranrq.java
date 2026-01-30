package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 Q001Tranrq 物件代表「查詢店家」交易所需要傳入的請求資料。
 * 包含了查詢條件 (storeId, storeName) 以及分頁資訊 (page)。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class Q001Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 店家 ID (用於精確查詢)
    private Integer storeId;

    // 店家名稱 (用於模糊查詢)
    @NotNull(message = "姓名不可為空")
    @Size(min = 1, max = 20)
    private String storeName;

    // 分頁資訊物件 (包含了頁碼和每頁筆數)
    @NotNull
    private Q001TranrqPage page;
}
