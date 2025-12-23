package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Car;
import com.example.demo.entity.Emp;
import com.example.demo.service.CarService;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/api/v1")
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/jpqlDemo1", method = RequestMethod.POST)
    public Car jpqlDemo1(String id) {
        return carService.jpqlDemo1(id);

    }

    @RequestMapping(value = "/jpqlDemo2", method = RequestMethod.POST)
    public Emp jpqlDemo2(String empId) {
        return empService.jpqlDemo2(empId);
    }

}
