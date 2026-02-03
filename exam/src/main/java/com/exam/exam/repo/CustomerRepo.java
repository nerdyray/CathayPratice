package com.exam.exam.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.entity.CustomerEntity;


public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    boolean existsByIdNum(String idNum);

    Page<CustomerEntity> findAll(Pageable pageable);
}
