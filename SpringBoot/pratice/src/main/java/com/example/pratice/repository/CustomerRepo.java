package com.example.pratice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pratice.entity.CustomerEntity;
import java.util.Optional;

/**
 * JPA方法
 */
@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {
    // 根據CustomerId查詢是否存在
    boolean existsByCustomerId(Long customerId);

    // Optional類別方法查詢Entity
    Optional<CustomerEntity> findByCustomerId(Long customerId);

}
