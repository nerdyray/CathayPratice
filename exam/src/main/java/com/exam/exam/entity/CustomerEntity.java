package com.exam.exam.entity;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代表客戶資訊的資料庫實體。
 * 對應到資料庫中的 `TB_CUSTOMER_INFO` 表格。
 */
@Entity
@Builder
@Table(name = "TB_CUSTOMER_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    /**
     * 訂單 ID，作為主鍵。
     * 使用資料庫的自增策略 (Identity) 來產生。
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;

    /**
     * 身分證字號。
     */
    @Column(name = "ID_NUM", length = 20)
    private String idNum;

    /**
     * 中文姓名。
     */
    @Column(name = "CHINESE_NAME", length = 20)
    private String chineseName;

    /**
     * 性別。
     */
    @Column(name = "GENDER", length = 20)
    private String gender;

    /**
     * 學歷。
     */
    @Column(name = "EDUCATION", length = 20)
    private String education;

    /**
     * 戶籍地郵遞區號。
     */
    @JsonAlias("zipCode_1")
    @Column(name = "ZIP_CODE_1", length = 20)
    private String zipCode1;

    /**
     * 戶籍地址。
     */
    @Column(name = "ADDRESS_1", length = 50)
    private String address1;

    /**
     * 戶籍地電話。
     */
    @Column(name = "TELEPHONE_1", length = 20)
    private String telephone1;

    /**
     * 現居地郵遞區號。
     */
    @JsonAlias("zipCode_2")
    @Column(name = "ZIP_CODE_2", length = 20)
    private String zipCode2;

    /**
     * 現居地址。
     */
    @Column(name = "ADDRESS_2", length = 50)
    private String address2;

    /**
     * 現居地電話。
     */
    @Column(name = "TELEPHONE_2", length = 20)
    private String telephone2;

    /**
     * 行動電話。
     */
    @Column(name = "MOBILE", length = 20)
    private String mobile;

    /**
     * 電子郵件。
     */
    @Column(name = "EMAIL", length = 20)
    private String email;

    /**
     * 年資。
     */
    @Column(name = "YEAR")
    private Integer year;
}
