package com.store.demo.repo;

import java.time.LocalDateTime;

public interface StoreProjection {
    Integer getStoreId();

    String getStoreName();

    String getOwner();

    String getTel();

    String getFax();

    String getMobile();

    String getAddress();

    String getEvaluation(); // 代碼

    String getRemarks();

    LocalDateTime getUpdateTime();

    String getUpdateUser(); // 代碼
    // ★ 來自關聯資料表 (Join) 的新欄位
    // 對應 SQL 中的 AS updateUserName

    String getUpdateUserName();

    // 對應 SQL 中的 AS evaluationName
    String getEvaluationName();
}