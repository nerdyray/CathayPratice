package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Emp;

@Repository
public interface  EmpRepository extends JpaRepository<Emp, String> {

    @Query(value = "select e from Emp e where e.empId=?1")
    public Emp jpqlDemo1(String empId);

    @Query(value = "select e from Emp e where e.empId = :EMP_ID")
    public Emp jpqlDemo2(@Param("EMP_ID") String empId);


}
