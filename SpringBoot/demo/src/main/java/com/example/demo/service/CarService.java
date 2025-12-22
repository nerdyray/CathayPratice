package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;

@Service
public interface CarService {



    public List<Car> queryAllCar();
    void insertCar();

}
