package com.example.pratice.svc.svcImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.entity.CustomerEntity;
import com.example.pratice.repository.CustomerRepo;
import com.example.pratice.svc.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public CustomerEntity createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        String name = customerRequest.getName();
        int birthday= customerRequest.getBirthday();
        String sex = customerRequest.getSex();
        String id =customerRequest.getId();

        customerRepo.save(customerEntity);
        return null;
    }

    @Override
    public List<CustomerEntity> searchCustomer(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchCustomer'");
    }

}
