package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class Q002TranrsItems implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("idNum")
    private String idNum;
    private String chineseName;
    private String gender;
    private String education;
    private String zipCode1;
    private String address1;
    private String telephone1;
    private String zipCode2;
    private String address2;
    private String telephone2;
    private String mobile;
    private String email;
    private Integer year;
}
