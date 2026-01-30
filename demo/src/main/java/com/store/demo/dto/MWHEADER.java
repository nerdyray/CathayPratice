package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO (Data Transfer Object): 資料傳輸物件。
 * 這個 MwHeader 物件代表所有 API 回應中都會包含的共用標頭 (Header) 資訊。
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * implements Serializable: 表示這個物件可以被序列化。
 */
@Data
public class MwHeader implements Serializable {

    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @JsonProperty("MSGID"):
     *   指定這個屬性在轉換成 JSON 格式時，對應的鍵 (key) 是 "MSGID"。
     *   例如，`private String msgid;` 會轉換成 `{"MSGID": "some_value"}`。
     */
    @JsonProperty("MSGID")
    private String msgid; // 訊息 ID

    // 對應 JSON 中的 "RETURNCODE"
    @JsonProperty("RETURNCODE")
    private String returncode; // 回應代碼

    // 對應 JSON 中的 "RETURNDESC"
    @JsonProperty("RETURNDESC")
    private String returndesc; // 回應描述

}