package com.exam.exam.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CommCodeEntityPK {
    @JsonProperty("MsgCode")
    @Column(name = "MSG_CODE", length = 30, nullable = false)
    private String msgCode;

    @JsonProperty("MsgOption")
    @Column(name = "MSG_OPTION", length = 20, nullable = false)
    private String msgOption;

    @JsonProperty("MsgOptionSerno")
    @Column(name = "MSG_OPTION_SERNO", nullable = false)
    private Integer msgOptionSerno;
}
