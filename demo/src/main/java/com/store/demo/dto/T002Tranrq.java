package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * DTO (Data Transfer Object)：資料傳輸物件。
 * 主要用於在不同層之間傳遞資料，例如從 Controller 到 Service，或是在 API 請求與回應中使用。
 * 這個 T002Tranrq 物件代表「新增店家」交易所需要傳入的請求資料。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化，通常在網路傳輸或儲存時需要。
 */
@Data
public class T002Tranrq implements Serializable {

    // @Serial: 用於標記序列化版本 ID 的欄位。
    @Serial
    private static final long serialVersionUID = 1L;

    // @NotNull: 驗證欄位，確保傳入的值不能是 null。
    // message: 當驗證失敗時顯示的錯誤訊息。
    private Integer storeId;

    @NotNull(message = "姓名不可為空")
    // @Size: 驗證字串的長度。
    @Size(min = 1, max = 20)
    private String storeName;

    @Size(min = 1, max = 20)
    private String owner;

    @NotNull(message = "電話不可為空")
    @Size(min = 1, max = 15)
    private String tel;

    @Size(min = 1, max = 15)
    private String fax;

    @Size(min = 1, max = 15)
    private String mobile;

    @Size(min = 1, max = 100)
    private String address;

    @Size(min = 1, max = 1)
    // @JsonProperty: 指定這個欄位在轉換成 JSON 時的名稱。
    @JsonProperty("evaluation")
    private String evaluation;

    @Size(min = 1, max = 100)
    private String remarks;

    @Size(min = 1, max = 20)
    private String date;
}