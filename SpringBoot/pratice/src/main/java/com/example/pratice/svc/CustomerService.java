package com.example.pratice.svc;

import java.util.List;

import com.example.pratice.dto.CUSTQ001Tranrs;
import com.example.pratice.dto.CUSTT001Tranrq;
import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CUSTT002Tranrq;
import com.example.pratice.dto.CUSTT002Tranrs;
import com.example.pratice.dto.CUSTT003Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.exception.DataNotFoundException;
import com.example.pratice.exception.ErrorInputException;

/**
 * 方法介面
 */
public interface CustomerService {

        CustomerResponse<CUSTT001Tranrs> createCustomer(CustomerRequest<CUSTT001Tranrq> customerRequest)
                        throws ErrorInputException;

        CustomerResponse<CUSTQ001Tranrs> searchCustomer(Long customerId)
                        throws ErrorInputException, DataNotFoundException;

        CustomerResponse<CUSTT002Tranrs> updateCustomer(CustomerRequest<CUSTT002Tranrq> customerRequest)
                        throws ErrorInputException, DataNotFoundException;

        CustomerResponse<CUSTT003Tranrs> deleteCustomer(Long customerId)
                        throws ErrorInputException, DataNotFoundException;

}
