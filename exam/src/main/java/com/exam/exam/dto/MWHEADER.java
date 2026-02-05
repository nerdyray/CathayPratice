package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 訊息標頭 (Message Header) 的資料傳輸物件 (DTO)。
 * 封裝了所有請求和回應共用的元數據。
 */
@Data
public class MWHEADER implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 訊息 ID。
     * 在 JSON 中對應的鍵為 "MSGID"。
     */
    @JsonProperty("MSGID")
    private String msgid;

    /**
     * 回應代碼。
     * 用於表示交易的處理結果狀態（例如 "0000" 表示成功）。
     * 在 JSON 中對應的鍵為 "RETURNCODE"。
     */
    @JsonProperty("RETURNCODE")
    private String returncode;

    /**
     * 回應描述。
     * 提供關於回應代碼的文字說明（例如 "交易成功"）。
     * 在 JSON 中對應的鍵為 "RETURNDESC"。
     */
    @JsonProperty("RETURNDESC")
    private String returndesc;
}