package com.example.pratice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pratice.entity.CustomerEntity;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @PostMapping("/newCustomer")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity createCustomerEntity) {

        return createCustomerEntity;
    }

}
