package com.example.pratice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CUSTT001Tranrq {

    @JsonProperty("CustomerId")
    private Long customerId;
    @Size(min = 1, max = 50)
    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Birthday")
    private Integer birthday;
    @Size(max = 1)
    @Pattern(regexp = "^(f|m)$", message = "性別只能是 f 或 m")
    @JsonProperty("Sex")
    private String sex;
    @Pattern(regexp = "^[A-Z][12]\\d{8}$", message = "身分證字號格式不正確，需為第一碼大寫英文，第二碼1或2，共10碼")
    @JsonProperty("Id")
    private String id;
}
