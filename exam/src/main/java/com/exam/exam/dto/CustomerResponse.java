package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerResponse<T> implements Serializable {

    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;

    // 對應 JSON 的 "MWHEADER"
    // 固定的訊息標頭
    @JsonProperty("MWHEADER")
    private MWHEADER mwheader;
    // 對應 JSON 的 "TRANRS"
    // 泛型的交易回應資料本體
    @JsonProperty("TRANRS")
    private T tranrs;
}
