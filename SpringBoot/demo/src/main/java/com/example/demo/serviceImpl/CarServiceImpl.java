package com.example.demo.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> queryAllCar() {
        return carRepository.findAll();
    }

    @Override
    public void insertCar() {
        Car car = new Car();
        car.setManufacturer("Honda");
        car.setType("Sporty");
        car.setMinPrice(new BigDecimal(8));
        car.setPrice(new BigDecimal(88));
        carRepository.save(car);
        }
}
