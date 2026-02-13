package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Q004TranrsEducation implements Serializable {
    // 序列化版本 ID
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("MsgOption")
    private String msgOption;

    @JsonProperty("MsgOptionMemo")
    private String msgOptionMemo;

    @JsonProperty("MsgOptionSerno")
    private String msgOptionSerno;
}
