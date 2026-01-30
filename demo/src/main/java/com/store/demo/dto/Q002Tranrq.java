package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 Q002Tranrq 物件可能是用於某個查詢交易，只根據 storeId 來查詢。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class Q002Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 店家 ID
    private Integer storeId;
}
