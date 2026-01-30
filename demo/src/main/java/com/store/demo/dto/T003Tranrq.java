package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 T003Tranrq 物件代表「刪除店家」交易所需要傳入的請求資料。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class T003Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 要刪除的店家 ID
    private Integer storeId;
}
