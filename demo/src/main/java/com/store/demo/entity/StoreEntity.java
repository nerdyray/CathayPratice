package com.store.demo.entity;

import java.time.LocalDateTime;

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

/**
 * Entity (實體): 這是一個對應到資料庫表格 (Table) 的類別。
 * ORM (Object-Relational Mapping) 框架 (例如 JPA/Hibernate) 會將這個類別的物件與資料庫中的紀錄進行映射。
 *
 * @Data (Lombok): 自動產生 getter, setter, toString, equals, hashCode 等方法。
 * @Builder (Lombok): 提供流暢的 Builder API 來建立物件。
 * @Entity: 告訴 JPA，這是一個實體類別。
 * @Table(name = "TB_STORE"): 指定這個實體對應到資料庫中的 "TB_STORE" 表格。
 * @NoArgsConstructor (Lombok): 自動產生無參數的建構子。
 * @AllArgsConstructor (Lombok): 自動產生包含所有屬性的建構子。
 * @EntityListeners(AuditingEntityListener.class): 啟用 JPA Auditing 功能，
 *   讓 @CreatedBy, @LastModifiedBy, @LastModifiedDate 等註解生效。
 */
@Data
@Builder
@Entity
@Table(name = "TB_STORE")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class StoreEntity {

    /**
     * @Id: 標記這個屬性是資料庫表格的主鍵 (Primary Key)。
     * @Column(name = "STORE_ID", length = 5, nullable = false):
     *   指定這個屬性對應到 "STORE_ID" 欄位，長度為 5，且不允許為 null。
     * @GeneratedValue(strategy = GenerationType.IDENTITY):
     *   指定主鍵的生成策略為 IDENTITY，表示由資料庫自動增長 (auto-increment)。
     */
    @Id
    @Column(name = "STORE_ID", length = 5, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    // 店家名稱
    @Column(name = "STORE_NAME", length = 20, nullable = false)
    private String storeName;

    // 負責人
    @Column(name = "OWNER", length = 20)
    private String owner;

    // 電話
    @Column(name = "TEL", length = 20, nullable = false)
    private String tel;

    // 傳真
    @Column(name = "FAX", length = 20)
    private String fax;

    // 手機
    @Column(name = "MOBILE", length = 20)
    private String mobile;

    // 地址
    @Column(name = "ADDRESS", length = 200)
    private String address;

    // 評價
    @Column(name = "EVALUATION", length = 20)
    private String evaluation;

    // 備註
    @Column(name = "REMARKS", length = 20)
    private String remarks;

    /**
     * @LastModifiedDate:
     *   當實體被更新時，JPA Auditing 會自動將這個欄位的值設定為當前的日期時間。
     */
    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * @CreatedBy: 當實體第一次被儲存時，JPA Auditing 會自動填入當前的操作者。
     * @LastModifiedBy: 當實體被更新時，JPA Auditing 會自動更新為當前的操作者。
     * 操作者的資訊來自於 JpaAuditingConfig 中定義的 AuditorAware Bean。
     */
    @CreatedBy
    @LastModifiedBy
    @Column(name = "UPDATE_USER", length = 20)
    private String updateUser;
}