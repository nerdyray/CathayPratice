package com.store.demo.entity;

import java.time.LocalDateTime; // 如果是 Spring Boot 2，請改用 javax.persistence

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "TB_STORE")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StoreEntity {

    @Id
    @Column(name = "STORE_ID", length = 5, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String storeId;

    /**
     * 店家名稱 對應 JSON: "store_name"
     */
    @Column(name = "STORE_NAME", length = 20, nullable = false)
    private String storeName;

    /**
     * 負責人 對應 JSON: "owner"
     */
    @Column(name = "OWNER", length = 20)
    private String owner;

    /**
     * 電話 對應 JSON: "tel"
     */
    @Column(name = "TEL", length = 20, nullable = false)
    private String tel;

    /**
     * 傳真 對應 JSON: "fax"
     */
    @Column(name = "FAX", length = 20)
    private String fax;

    /**
     * 手機 對應 JSON: "mobile"
     */
    @Column(name = "MOBILE", length = 20)
    private String mobile;

    /**
     * 地址 對應 JSON: "address"
     */
    @Column(name = "ADDRESS", length = 200)
    private String address;

    /**
     * 評價 對應 JSON: "evaluation"
     */
    @Column(name = "EVALUATION", length = 20)
    private String evaluation;

    /**
     * 備註 對應 JSON: "remarks"
     */
    @Column(name = "REMARKS", length = 20)
    private String remarks;

    /**
     * 異動時間 對應 JSON: "date" ("05/10/2021") 注意：需將 String 轉換為 LocalDateTime
     */
    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 異動人員 JSON 中無此欄位，建議在 Service 層補上
     */
    @CreatedBy
    @LastModifiedBy
    @Column(name = "UPDATE_USER", length = 20)
    private String updateUser;
}