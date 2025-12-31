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

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pratice.dto.CUSTQ001Tranrs;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/newCustomer")
    public CustomerResponse<CUSTT001Tranrs> createCustomer(
            @Valid @RequestBody CustomerRequest<CUSTT001Tranrq> customerRequest) {

        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("/{id}")
    public CustomerResponse<CUSTQ001Tranrs> searchCustomer(@Valid @PathVariable("id") String id) {
        return customerService.searchCustomer(id);
    }

}
