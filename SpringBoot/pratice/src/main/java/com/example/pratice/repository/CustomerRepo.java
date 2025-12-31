package com.example.pratice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pratice.entity.CustomerEntity;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    boolean existsByCustomerId(Long customerId);

    Optional<CustomerEntity> findById(String id);
}
