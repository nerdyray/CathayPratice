package com.example.pratice.svc;

import java.util.List;

import com.example.pratice.dto.CUSTQ001Tranrs;
import com.example.pratice.dto.CUSTT001Tranrq;
import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.entity.CustomerEntity;


public interface CustomerService {

    CustomerResponse<CUSTT001Tranrs> createCustomer(CustomerRequest<CUSTT001Tranrq> customerRequest);


    CustomerResponse <CUSTQ001Tranrs> searchCustomer(String id);

}
