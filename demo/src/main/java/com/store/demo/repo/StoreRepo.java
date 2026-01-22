package com.store.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.store.demo.entity.StoreEntity;

public interface StoreRepo extends JpaRepository<com.store.demo.entity.StoreEntity, Integer> {
    boolean existsByStoreId(Integer storeId);

    Page<StoreEntity> findAllByStoreName(String storeName, Pageable pageable);
}