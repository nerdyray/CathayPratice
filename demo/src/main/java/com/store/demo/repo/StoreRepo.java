package com.store.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<com.store.demo.entity.StoreEntity, String> {
    boolean existsByStoreId(String storeId);
}