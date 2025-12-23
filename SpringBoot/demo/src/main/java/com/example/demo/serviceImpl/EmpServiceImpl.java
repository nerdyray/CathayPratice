package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Emp;
import com.example.demo.repository.EmpRepository;
import com.example.demo.service.EmpService;

@Service

public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpRepository empRepository;

    @Override
    public Emp jpqlDemo1(String empId){
        return empRepository.jpqlDemo1(empId);
    }
    @Override
    public Emp jpqlDemo2(String empId){
        return empRepository.jpqlDemo2(empId);
    }
}
