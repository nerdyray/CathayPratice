package com.exam.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.exam.entity.CustomerEntity;

/**
 * 通用代碼的 JPA Repository。
 * 注意：目前此介面繼承自 JpaRepository<CustomerEntity, Integer>，
 * 這可能與其名稱 "CommomCodeRepo" 不符。預期可能應為 CommCodeEntity。
 */
public interface CommomCodeRepo extends JpaRepository<CustomerEntity, Integer> {

}
