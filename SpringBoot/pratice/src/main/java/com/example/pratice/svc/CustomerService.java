package com.example.pratice.svc;

import com.example.pratice.entity.CustomerEntity;

public interface CustomerService {
    /**
     * 新增查詢修改方法介面
     * Customer_ID要以localDate+DateFormmater格式化
     */
    CustomerEntity createCustomer();
}
