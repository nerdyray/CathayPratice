package com.exam.exam.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "TB_CUSTOMER_INFO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "ID_NUM", length = 20)
    private String idNum;

    @Column(name = "CHINESE_NAME", length = 20)
    private String chineseName;

    @Column(name = "GENDER", length = 20)
    private String gender;

    @Column(name = "EDUCATION", length = 20)
    private String education;

    @Column(name = "ZIP_CODE_1", length = 20)
    private String zipCode1;

    @Column(name = "ADDRESS_1", length = 50)
    private String address1;

    @Column(name = "TELEPHONE_1", length = 20)
    private String telephone1;

    @Column(name = "ZIP_CODE_2", length = 20)
    private String zipCode2;

    @Column(name = "ADDRESS_2", length = 50)
    private String address2;

    @Column(name = "TELEPHONE_2", length = 20)
    private String telephone2;

    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Column(name = "EMAIL", length = 20)
    private String email;

    @Column(name = "YEAR")
    private Integer year;
}
