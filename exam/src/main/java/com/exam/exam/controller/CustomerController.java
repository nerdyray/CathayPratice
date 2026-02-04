package com.exam.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.Q001Tranrq;
import com.exam.exam.dto.Q001Tranrs;
import com.exam.exam.dto.Q002Tranrq;
import com.exam.exam.dto.Q002Tranrs;
import com.exam.exam.dto.Q003Tranrq;
import com.exam.exam.dto.Q003Tranrs;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.dto.T002Tranrq;
import com.exam.exam.dto.T002Tranrs;
import com.exam.exam.exception.DataNotFoundException;
import com.exam.exam.exception.DuplicateDataException;
import com.exam.exam.exception.ErrorInputException;
import com.exam.exam.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/cif")
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
        return customerService.createCustomer(customerRequest);
    }

    @PostMapping("filter")
    public CustomerResponse<Q002Tranrs> findAllCustomer(
            @Valid @RequestBody CustomerRequest<Q002Tranrq> customerRequest) throws DataNotFoundException {
        return customerService.findAllCustomer(customerRequest);
    }

    @PostMapping("getOne")
    public CustomerResponse<Q001Tranrs> findByOrderId(
            @Valid @RequestBody CustomerRequest<Q001Tranrq> customerRequest) throws DataNotFoundException {
        return customerService.findByOrderId(customerRequest);
    }

    @PostMapping("checkId")
    public CustomerResponse<Q003Tranrs> excisedByIdNum(
            @Valid @RequestBody CustomerRequest<Q003Tranrq> customerRequest) throws DataNotFoundException {
        return customerService.excisedByIdNum(customerRequest);
    }

    @PostMapping("editInfo")
    public CustomerResponse<T002Tranrs> updateCustomer(@Valid @RequestBody CustomerRequest<T002Tranrq> customerRequest,
            Errors err) throws ErrorInputException, DataNotFoundException {
        if (err.hasErrors()) {
            throw new ErrorInputException();
        }
        return customerService.updateCustomer(customerRequest);
    }
}