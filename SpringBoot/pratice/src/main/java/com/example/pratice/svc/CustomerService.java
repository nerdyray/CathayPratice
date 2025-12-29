package com.example.pratice.svc;

import java.util.List;

import com.example.pratice.dto.CUSTT001Tranrq;
import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.entity.CustomerEntity;

/**
 * 新增查詢修改方法介面 CustomerId要以localDate+DateFormmater格式化
 */
public interface CustomerService {

    CustomerResponse<CUSTT001Tranrs> createCustomer(CustomerRequest<CUSTT001Tranrq> customerRequest);


    List<CustomerEntity> searchCustomer(Long id);

}
