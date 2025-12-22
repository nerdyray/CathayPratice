package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Car;
import com.example.demo.entity.CarPK;

import lombok.Data;
@Repository
public interface CarRepository extends JpaRepository<Car,CarPK> {

    public List<Car> findByManufacturerAndType(String manu, String type);
}
