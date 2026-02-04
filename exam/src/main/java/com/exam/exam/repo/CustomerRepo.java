package com.exam.exam.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.entity.CustomerEntity;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    boolean existsByIdNum(String idNum);

    Page<CustomerEntity> findAll(Pageable pageable);

    Optional<CustomerEntity> findByOrderId(Integer orderId);



}
