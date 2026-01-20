package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StoreEntity;

public interface  StoreRepo extends JpaRepository<StoreEntity,String>{
    
}
