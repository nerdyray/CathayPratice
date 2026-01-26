package com.store.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class T001Tranrq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "客戶編號不可為空")
    private Integer storeId;
    @NotNull(message = "姓名不可為空")
    @Size(min = 1, max = 20)
    private String storeName;
    @Size(min = 1, max = 20)
    private String owner;
    @NotNull(message = "電話不可為空")
    @Size(min = 1, max = 15)
    private String tel;
    @Size(min = 1, max = 15)
    private String fax;
    @Size(min = 1, max = 15)
    private String mobile;
    @Size(min = 1, max = 100)
    private String address;
    @Size(min = 1, max = 1)
    private String evaluation;
    @Size(min = 1, max = 100)
    private String remarks;
    @Size(min = 1, max = 20)
    private String date;
}
