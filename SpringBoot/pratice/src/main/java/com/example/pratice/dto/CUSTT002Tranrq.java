package com.example.pratice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CUSTT002Tranrq {
    @NotNull(message = "客戶編號不能為空")
    @Min(value = 1000000000000L, message = "客戶編號必須為 13 位數")
    @Max(value = 9999999999999L, message = "客戶編號長度超出限制")
    @JsonProperty("CustomerId")
    private Long customerId;
    @Size(min = 1, max = 50)
    @NotBlank(message = "Name is required")
    @JsonProperty("Name")
    private String name;
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "生日格式錯誤，必須為 yyyy-MM-dd")
    @JsonProperty("Birthday")
    private String birthday;
    @Size(max = 1)
    @Pattern(regexp = "^(f|m)$", message = "性別只能是 f 或 m")
    @JsonProperty("Sex")
    private String sex;
    @Pattern(regexp = "^[A-Z][12]\\d{8}$", message = "身分證字號格式不正確，需為第一碼大寫英文，第二碼1或2，共10碼")
    @JsonProperty("Id")
    private String id;
}
