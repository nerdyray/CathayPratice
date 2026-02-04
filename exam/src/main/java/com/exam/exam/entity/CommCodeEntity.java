package com.exam.exam.entity; // 請依照你的 package 修改

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_COMMCODE")
public class CommCodeEntity {

    /**
     * 代碼 (Primary Key 1)
     */
    @Id
    @Column(name = "MSG_CODE", length = 30, nullable = false)
    private String msgCode;

    /**
     * 代碼說明
     */
    @Column(name = "MSG_CODE_MEMO", length = 20)
    private String msgCodeMemo;

    /**
     * 代碼選項 (Primary Key 2)
     */

    @Column(name = "MSG_OPTION", length = 20, nullable = false)
    private String msgOption;

    /**
     * 代碼選項名稱
     */
    @Column(name = "MSG_OPTION_MEMO", length = 20)
    private String msgOptionMemo;

    /**
     * 代碼選項順序 (Primary Key 3)
     */
    @Column(name = "MSG_OPTION_SERNO", nullable = false)
    private Integer msgOptionSerno;

    /**
     * 異動人員行編
     */
    @Column(name = "UP_EMPID", length = 10)
    private String upEmpid;

    /**
     * 異動日期
     * 對應 SQL 的 TIMESTAMP
     */
    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;
}