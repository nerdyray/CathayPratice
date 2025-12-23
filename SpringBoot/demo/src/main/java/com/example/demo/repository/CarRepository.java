package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query("select e from Car e where e.id = ?1")
    Car jpqlDemo1(String id);

    public List<Car> findBymanufacturerAndType(String manu, String type);
}
