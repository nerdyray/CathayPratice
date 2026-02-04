package com.exam.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.entity.CustomerEntity;

public interface CommomCodeRepo extends JpaRepository<CustomerEntity, Integer> {

}
