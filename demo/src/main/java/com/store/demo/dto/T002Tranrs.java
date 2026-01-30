package com.store.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 T002Tranrs 物件代表「新增店家」交易成功後的回應資料。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class T002Tranrs implements Serializable {
    // 序列化版本 ID
    private static final long serialVersionUID = 1L;

    // 對應 JSON 的 "TRANRS"
    // 與 T001Tranrs 類似，這裡的回應資料本體也是一個空的 TRANRS 物件。
    @JsonProperty("TRANRS")
    private TRANRS tranrs;
}