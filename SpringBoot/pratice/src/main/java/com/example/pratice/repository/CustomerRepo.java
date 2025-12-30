package com.example.pratice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

import com.example.pratice.dto.CUSTQ001Tranrs;
import com.example.pratice.dto.CustomerResponse;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    boolean existsByCustomerId(Long customerId);

    Optional<CustomerEntity> findById(String id);
}
