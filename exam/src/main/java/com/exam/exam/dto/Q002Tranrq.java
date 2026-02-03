package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Q002Tranrq implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("PAGE")
    private Q002TranrqPage page;
    @JsonProperty("DATA")
    private TranData tranrqData;
    @JsonProperty("STOREINFO")
    private Q002TranrqSortInfo storeInfo;

}
