package com.exam.exam.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用於 JPA Specification 動態查詢的條件封裝物件 (DTO)。
 * 已移除驗證註解，僅保留資料欄位用於查詢過濾。
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 訂單 ID */
    private Integer orderId;

    /** 身分證字號 */
    private String idNum;

    /** 中文姓名 */
    private String chineseName;

    /** 性別 */
    private String gender;

    /** 學歷 */
    private String education;

    /** 戶籍地郵遞區號 */
    private String zipCode1;

    /** 戶籍地址 */
    private String address1;

    /** 戶籍地電話 */
    private String telephone1;

    /** 現居地郵遞區號 */
    private String zipCode2;

    /** 現居地址 */
    private String address2;

    /** 現居地電話 */
    private String telephone2;

    /** 行動電話 */
    private String mobile;

    /** 電子郵件 */
    private String email;

    /** 年資 */
    private Integer year;

}