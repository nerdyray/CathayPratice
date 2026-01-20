package com.example.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MwHeader implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @JsonProperty("MSGID")
    private String msgid;
    @JsonProperty("RETURNCODE")
    private String returncode;
    @JsonProperty("RETURNDESC")
    private String returndesc;

}
