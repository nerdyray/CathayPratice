package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 T003Tranrs 物件代表「刪除店家」交易成功後的回應資料。
 * 通常刪除交易成功後，回應的資料本體是空的。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class T003Tranrs implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

}
