package com.store.demo.dto;

import java.io.Serial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 Q001TranrsItems 物件代表查詢結果列表中的單一一個店家項目。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 */
@Data
public class Q001TranrsItems {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 店家 ID
    @NotNull(message = "客戶編號不可為空")
    private Integer storeId;

    // 店家名稱
    @NotNull(message = "姓名不可為空")
    @Size(min = 1, max = 20)
    private String storeName;

    // 負責人
    @Size(min = 1, max = 20)
    private String owner;

    // 電話
    @NotNull(message = "電話不可為空")
    @Size(min = 1, max = 15)
    private String tel;

    // 傳真
    @Size(min = 1, max = 15)
    private String fax;

    // 手機
    @Size(min = 1, max = 15)
    private String mobile;

    // 地址
    @Size(min = 1, max = 100)
    private String address;

    // 評價 (代碼)
    @Size(min = 1, max = 1)
    private String evaluation;

    // 備註
    @Size(min = 1, max = 100)
    private String remarks;

    // 最後更新時間 (字串格式)
    @Size(min = 1, max = 20)
    private String updateTime;

    // 最後更新人員姓名
    private String updateUserName;

    // 評價名稱
    private String evaluationName;

    // 最後更新人員代號
    private String updateUser;
}
