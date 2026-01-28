package com.store.demo.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.store.demo.entity.StoreEntity;

public interface StoreRepo extends JpaRepository<com.store.demo.entity.StoreEntity, Integer> {
    boolean existsByStoreId(Integer storeId);

    Page<StoreEntity> findAllByStoreNameContaining(String storeName, Pageable pageable);

    Optional<StoreEntity> findByStoreId(Integer storeId);

    Optional<StoreEntity> deleteByStoreId(Integer storeId);

    @Query(value = """
            SELECT
                s.STORE_ID AS storeId,
                s.STORE_NAME AS storeName,
                s.OWNER AS owner,
                s.TEL AS tel,
                s.FAX AS fax,
                s.MOBILE AS mobile,
                s.ADDRESS AS address,
                s.EVALUATION AS evaluation,
                s.REMARKS AS remarks,
                s.UPDATE_TIME AS updateTime,
                s.UPDATE_USER AS updateUser,

                e.EMP_NAME AS updateUserName,       -- 抓出員工姓名，塞給介面的 getUpdateUserName()
                c.MSG_OPTION_MEMO AS evaluationName        -- 抓出評價名稱，塞給介面的 getEvaluationName()

            FROM TB_STORE s
            LEFT JOIN TB_EMP e ON s.UPDATE_USER = e.EMP_ID
            LEFT JOIN TB_COMMCODE c ON s.EVALUATION = c.MSG_OPTION AND c.MSG_CODE = 'Evaluation'
            WHERE (:storeName IS NULL OR s.STORE_NAME LIKE CONCAT('%', :storeName, '%'))
            """, nativeQuery = true)
    Page<StoreProjection> findStoresWithDetails(@Param("storeName") String storeName, Pageable pageable);
}