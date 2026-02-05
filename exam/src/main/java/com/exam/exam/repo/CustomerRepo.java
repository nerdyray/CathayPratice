package com.exam.exam.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.entity.CustomerEntity;
import java.util.Optional;

/**
 * 客戶資料的 JPA Repository，提供對 {@link CustomerEntity} 的資料庫操作。
 */
public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    /**
     * 檢查具有指定身分證號碼的客戶是否存在。
     *
     * @param idNum 要檢查的身分證號碼。
     * @return 如果存在則為 true，否則為 false。
     */
    boolean existsByIdNum(String idNum);

    /**
     * 查詢所有客戶並以分頁形式返回。
     *
     * @param pageable 分頁資訊。
     * @return 包含客戶實體的分頁物件。
     */
    Page<CustomerEntity> findAll(Pageable pageable);

    /**
     * 根據訂單 ID 查找客戶。
     *
     * @param orderId 要查找的客戶訂單 ID。
     * @return 包含客戶實體的 {@link Optional}，如果找不到則為空。
     */
    Optional<CustomerEntity> findByOrderId(Integer orderId);

    /**
     * 根據訂單 ID 刪除客戶。
     *
     * @param orderId 要刪除的客戶訂單 ID。
     * @return 包含已刪除客戶實體的 {@link Optional}，如果找不到則為空。
     */
    Optional<CustomerEntity> deleteByOrderId(Integer orderId);

}
