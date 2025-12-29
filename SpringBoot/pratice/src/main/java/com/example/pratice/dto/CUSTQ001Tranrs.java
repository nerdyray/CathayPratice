package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
@Data
public class CUSTQ001Tranrs {

    @JsonProperty("DATAS")
    private  List<CUSTQ001TranrsDatas> datas;
    @JsonProperty("MESSAGE")
    private String message;


}
