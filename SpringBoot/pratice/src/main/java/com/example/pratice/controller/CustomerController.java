package com.example.pratice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pratice.dto.CUSTT001Tranrq;
import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.svc.CustomerService;
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/newCustomer")
    public CustomerResponse<CUSTT001Tranrs> createCustomer(@RequestBody CustomerRequest<CUSTT001Tranrq> customerRequest) {
        
        return customerService.createCustomer(customerRequest);
    }

}
