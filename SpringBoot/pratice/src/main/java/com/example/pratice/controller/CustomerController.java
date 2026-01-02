package com.example.pratice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pratice.dto.CUSTT001Tranrq;
import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CUSTT002Tranrq;
import com.example.pratice.dto.CUSTT002Tranrs;
import com.example.pratice.dto.CUSTT003Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.exception.DataNotFoundException;
import com.example.pratice.exception.ErrorInputException;
import com.example.pratice.svc.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.pratice.dto.CUSTQ001Tranrs;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/newCustomer")
    public CustomerResponse<CUSTT001Tranrs> createCustomer(
            @Valid @RequestBody CustomerRequest<CUSTT001Tranrq> customerRequest, Errors err)
            throws ErrorInputException, DataNotFoundException {
        if (err.hasErrors()) {
            System.err.println(err);
            throw new ErrorInputException();
        }
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("/{CustomerId}")
    public CustomerResponse<CUSTQ001Tranrs> searchCustomer(@Valid @PathVariable("CustomerId") Long customerId)
            throws ErrorInputException, DataNotFoundException {

        return customerService.searchCustomer(customerId);
    }

    @PostMapping("/updateCustomer")
    public CustomerResponse<CUSTT002Tranrs> updateCustomer(
            @Valid @RequestBody CustomerRequest<CUSTT002Tranrq> customerRequest,
            Errors err) throws ErrorInputException, DataNotFoundException {
        if (err.hasErrors()) {
            throw new ErrorInputException();
        }
        return customerService.updateCustomer(customerRequest);
    }

    @GetMapping("/deleteCustomer/{CustomerId}")
    public CustomerResponse<CUSTT003Tranrs> deleteCustomer(@Valid @PathVariable("CustomerId") Long customerId)
            throws ErrorInputException, DataNotFoundException {

        return customerService.deleteCustomer(customerId);
    }

}
