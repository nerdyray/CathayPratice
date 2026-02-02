package com.exam.exam.service;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.exception.DuplicateDataException;

public interface CustomerService {
    CustomerResponse<T001Tranrs> createStore(CustomerRequest<T001Tranrq> customerRequest)throws DuplicateDataException;
}
