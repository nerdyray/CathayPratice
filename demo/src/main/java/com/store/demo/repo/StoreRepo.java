package com.store.demo.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.demo.entity.StoreEntity;

/**
 * Repository (資料存取層): 這是 Spring Data JPA 的核心。
 * 透過繼承 JpaRepository，我們就能夠獲得一組預設的 CRUD (新增、讀取、更新、刪除) 方法，
 * 不需要自己寫 SQL 語法。
 * JpaRepository<StoreEntity, Integer> 中的 StoreEntity 是這個 Repository 操作的實體類別，
 * Integer 則是這個實體主鍵 (Primary Key) 的型別。
 */
public interface StoreRepo extends JpaRepository<com.store.demo.entity.StoreEntity, Integer> {
    /**
     * 根據 storeId 檢查資料是否存在。
     * Spring Data JPA 會自動根據方法名稱產生對應的查詢。
     * @param storeId 店家 ID
     * @return 如果存在則回傳 true，否則回傳 false。
     */
    boolean existsByStoreId(Integer storeId);

    /**
     * 根據店家名稱進行模糊查詢，並回傳分頁結果。
     * @param storeName 店家名稱的查詢關鍵字
     * @param pageable 分頁資訊物件
     * @return 包含 StoreEntity 的分頁結果
     */
    Page<StoreEntity> findAllByStoreNameContaining(String storeName, Pageable pageable);

    /**
     * 根據 storeId 查詢店家資料。
     * 回傳 Optional<StoreEntity> 是為了避免空指標例外 (NullPointerException)。
     * @param storeId 店家 ID
     * @return 一個可能包含 StoreEntity 的 Optional 物件
     */
    Optional<StoreEntity> findByStoreId(Integer storeId);

    /**
     * 根據 storeId 刪除店家資料。
     * @param storeId 店家 ID
     * @return 一個可能包含被刪除的 StoreEntity 的 Optional 物件
     */
    Optional<StoreEntity> deleteByStoreId(Integer storeId);

    /**
     * @Query：當預設的方法名稱無法滿足複雜的查詢需求時，可以使用這個註解來自行撰寫 SQL 語法。
     * value = """ ... """: 使用三個引號可以方便地撰寫多行 SQL 語法。
     * nativeQuery = true: 表示這是一段原生的 SQL 語法，而不是 JPQL (Java Persistence Query Language)。
     *
     * 這段 SQL 的功能是：
     * 1. 查詢 TB_STORE (s) 的所有欄位。
     * 2. 透過 LEFT JOIN 關聯 TB_EMP (e) 來取得異動人員的姓名 (updateUserName)。
     * 3. 透過 LEFT JOIN 關聯 TB_COMMCODE (c) 來取得評價的名稱 (evaluationName)。
     * 4. WHERE 子句用來做條件查詢，如果 :storeName 是 null 或空字串，就查詢所有資料；否則就進行模糊比對。
     *
     * @param storeName 傳入的店家名稱查詢參數
     * @param pageable 分頁資訊
     * @return 回傳一個分頁過的 StoreProjection 結果。StoreProjection 是一個介面，用來接收部分查詢結果。
     */
    @Query(value = """
            SELECT
                s.STORE_ID AS storeId,
                s.STORE_NAME AS storeName,
                s.OWNER AS owner,
                s.TEL AS tel,
                s.FAX AS fax,
                s.MOBILE AS mobile,
                s.ADDRESS AS address,
                s.REMARKS AS remarks,
                s.UPDATE_TIME AS updateTime,
                s.UPDATE_USER AS updateUser,
                e.EMP_NAME AS updateUserName,
                c.MSG_OPTION_MEMO AS evaluationName
            FROM TB_STORE s
            LEFT JOIN TB_EMP e ON s.UPDATE_USER = e.EMP_ID
            LEFT JOIN TB_COMMCODE c ON s.EVALUATION = c.MSG_OPTION AND c.MSG_CODE = 'Evaluation'
            WHERE (:storeName IS NULL OR s.STORE_NAME LIKE CONCAT('%', :storeName, '%'))
            """, nativeQuery = true)
    Page<StoreProjection> findStoresWithDetails(@Param("storeName") String storeName, Pageable pageable);
}