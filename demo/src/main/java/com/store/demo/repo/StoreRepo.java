package com.store.demo.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.store.demo.entity.StoreEntity;
import java.util.List;

public interface StoreRepo extends JpaRepository<com.store.demo.entity.StoreEntity, Integer> {
    boolean existsByStoreId(Integer storeId);

    Page<StoreEntity> findAllByStoreName(String storeName, Pageable pageable);

    Optional<StoreEntity> findByStoreId(Integer storeId);

    Optional<StoreEntity> deleteByStoreId(Integer storeId);
}