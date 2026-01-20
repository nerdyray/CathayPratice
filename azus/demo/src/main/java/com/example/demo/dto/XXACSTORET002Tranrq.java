package com.example.demo.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
@Data
public class XXACSTORET002Tranrq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String storeId;
    private String storeName;
    private String owner;
    private String tel;
    private String fax;
    private String mobile;
    private String address;
    private String evaluation;
    private String remarks;
    private String date;
}
