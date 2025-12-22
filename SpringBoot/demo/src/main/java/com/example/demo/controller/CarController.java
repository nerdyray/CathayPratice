package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.service.CarService;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/queryAllCar", method = RequestMethod.POST)
    public List<Car> queryAllCar() {
        return carService.queryAllCar();
    }
        @RequestMapping(value = "/insertCar", method = RequestMethod.GET)
        public void insertCar() {
            carService.insertCar();
    }
}
