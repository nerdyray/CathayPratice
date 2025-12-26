package com.example.pratice.svc.svcImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.entity.CustomerEntity;
import com.example.pratice.repository.CustomerRepo;
import com.example.pratice.svc.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        CustomerResponse customerResponse = new CustomerResponse();
        // 檢核

        // 主要操作
        customerEntity.setName(customerRequest.getName());
        CustomerEntity.builder()
                .name(customerRequest.getName())
                .birthday(customerRequest.getBirthday())
                .sex(customerRequest.getSex())
                .id(customerRequest.getId())
                .build();
        // 建封包(Response{TRANQ{}})
        customerResponse.setName(customerRequest.getName());

       
        return customerResponse;
    }

    @Override
    public List<CustomerEntity> searchCustomer(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchCustomer'");
    }

}
