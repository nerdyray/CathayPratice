package com.exam.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.exception.DuplicateDataException;
import com.exam.exam.exception.ErrorInputException;
import com.exam.exam.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("create")
    public CustomerResponse<T001Tranrs> createCustomer(@Valid @RequestBody CustomerRequest<T001Tranrq> customerRequest,
            Errors err) throws ErrorInputException, DuplicateDataException {
        if (err.hasErrors()) {
            throw new ErrorInputException();
        }
        return customerService.createStore(customerRequest);
    }

}
