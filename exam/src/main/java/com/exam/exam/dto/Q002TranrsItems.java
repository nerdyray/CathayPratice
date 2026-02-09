package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;

import com.exam.exam.dto.TranData.Q002Interface;
import com.exam.exam.dto.TranData.T002Interface;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Q002 交易回應中，單一客戶資料項目的資料傳輸物件 (DTO)。
 */
@Data
public class Q002TranrsItems implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("orderId")
    private Integer orderId;
    /**
     * 身分證字號。
     */
    @JsonProperty("idNum")
    private String idNum;

    /**
     * 中文姓名。
     */
    private String chineseName;

    /**
     * 性別。
     */
    private String gender;

    /**
     * 學歷。
     */
    private String education;

    /**
     * 戶籍地郵遞區號。
     */
    private String zipCode1;

    /**
     * 戶籍地址。
     */
    private String address1;

    /**
     * 戶籍地電話。
     */
    private String telephone1;

    /**
     * 現居地郵遞區號。
     */
    private String zipCode2;

    /**
     * 現居地址。
     */
    private String address2;

    /**
     * 現居地電話。
     */
    private String telephone2;

    /**
     * 行動電話。
     */
    private String mobile;

    /**
     * 電子郵件。
     */
    private String email;

    /**
     * 年資。
     */
    private Integer year;
}
