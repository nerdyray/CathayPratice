package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CarRequest;
import com.example.demo.dto.CarResponse;
import com.example.demo.entity.Car;
import com.example.demo.entity.Emp;

@Service
public interface CarService {

    public Car jpqlDemo1(String id);

    public List<Car> queryAllCar();

    void insertCar();

    CarResponse queryCar(CarRequest carRequest);

}
