package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 T001Tranrq 物件代表「更新店家」交易所需要傳入的請求資料。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class T001Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 店家 ID，必須提供，用來指定要更新哪一筆資料
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

    // 評價
    @Size(min = 1, max = 1)
    private String evaluation;

    // 備註
    @Size(min = 1, max = 100)
    private String remarks;

    // 日期 (此欄位用途不明確，可能為異動日期)
    @Size(min = 1, max = 20)
    private String date;
}
