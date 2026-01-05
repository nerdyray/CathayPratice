package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import lombok.Data;

/**
 * Tranrq的資料
 */
@Data
@JsonPropertyOrder({ "MESSAGE", "DATAS" })
public class CUSTQ001Tranrs {

    @JsonProperty("MESSAGE")
    private String message;
    @JsonProperty("DATAS")
    private List<CUSTQ001TranrsDatas> datas;

}
