package com.exam.exam.service;

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

public interface CustomerService {
        CustomerResponse<T001Tranrs> createCustomer(CustomerRequest<T001Tranrq> customerRequest)
                        throws DuplicateDataException;

        CustomerResponse<Q002Tranrs> findAllCustomer(CustomerRequest<Q002Tranrq> customerRequest)
                        throws DataNotFoundException;

        CustomerResponse<Q001Tranrs> findByOrderId(CustomerRequest<Q001Tranrq> customerRequest)
                        throws DataNotFoundException;

        CustomerResponse<Q003Tranrs> excisedByIdNum(CustomerRequest<Q003Tranrq> customerRequest)
                        throws DataNotFoundException;

        CustomerResponse<T002Tranrs> updateCustomer(CustomerRequest<T002Tranrq> customerRequest)
                        throws DataNotFoundException;
}
