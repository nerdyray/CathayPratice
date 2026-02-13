package com.exam.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代表通用代碼 (Common Code) 的資料庫實體。
 * 通常用於儲存下拉式選單、選項按鈕等靜態資料。
 * 表格名稱為 `TB_COMMCODE`。
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_COMMCODE")
@IdClass(CommCodeEntityPK.class)
public class CommCodeEntity {

    /**
     * 代碼。複合主鍵的一部分。
     */
    @Id
    @Column(name = "MSG_CODE", length = 30, nullable = false)
    private String msgCode;

    /**
     * 代碼的文字說明。
     */

    @Column(name = "MSG_CODE_MEMO", length = 20)
    private String msgCodeMemo;

    /**
     * 代碼的選項值。複合主鍵的一部分。
     */
    @Id
    @JsonProperty("MsgOption")
    @Column(name = "MSG_OPTION", length = 20, nullable = false)
    private String msgOption;

    /**
     * 代碼選項的文字說明。
     */

    @JsonProperty("MsgOptionMemo")
    @Column(name = "MSG_OPTION_MEMO", length = 20)
    private String msgOptionMemo;

    /**
     * 代碼選項的顯示序號。複合主鍵的一部分。
     */
    @Id
    @JsonProperty("MsgOptionSerno")
    @Column(name = "MSG_OPTION_SERNO", nullable = false)
    private Integer msgOptionSerno;

    /**
     * 最後異動此紀錄的員工編號。
     */
    @Column(name = "UP_EMPID", length = 10)
    private String upEmpid;

    /**
     * 紀錄的最後更新時間。
     * 對應到資料庫的 TIMESTAMP 類型。
     */
    @Column(name = "UPD_DATE")
    private LocalDateTime updDate;
}