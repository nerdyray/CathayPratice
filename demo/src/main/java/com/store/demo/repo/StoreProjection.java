package com.store.demo.repo;

import java.time.LocalDateTime;

/**
 * Projection (投影):
 * 在 Spring Data JPA 中，當我們不想要查詢出實體 (Entity) 的所有欄位，
 * 或是需要查詢關聯表格的欄位時，可以使用介面投影 (Interface-based Projection)。
 *
 * 這個介面定義了我們希望從資料庫查詢中回傳的特定欄位。
 * 每個 getter 方法的名稱對應到 @Query 查詢語法中 `AS` 後面的別名。
 * 例如，`getUpdateUserName()` 方法會接收 `e.EMP_NAME AS updateUserName` 的查詢結果。
 */
public interface StoreProjection {
    // 對應 s.STORE_ID
    Integer getStoreId();

    // 對應 s.STORE_NAME
    String getStoreName();

    // 對應 s.OWNER
    String getOwner();

    // 對應 s.TEL
    String getTel();

    // 對應 s.FAX
    String getFax();

    // 對應 s.MOBILE
    String getMobile();

    // 對應 s.ADDRESS
    String getAddress();

    // 對應 s.EVALUATION (代碼)
    String getEvaluation();

    // 對應 s.REMARKS
    String getRemarks();

    // 對應 s.UPDATE_TIME
    LocalDateTime getUpdateTime();

    // 對應 s.UPDATE_USER (代碼)
    String getUpdateUser();

    // 這是來自關聯表格 (LEFT JOIN TB_EMP) 的欄位
    // 對應 SQL 中的 `e.EMP_NAME AS updateUserName`
    String getUpdateUserName();

    // 這是來自關聯表格 (LEFT JOIN TB_COMMCODE) 的欄位
    // 對應 SQL 中的 `c.MSG_OPTION_MEMO AS evaluationName`
    String getEvaluationName();
}