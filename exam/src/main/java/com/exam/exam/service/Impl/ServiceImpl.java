package com.exam.exam.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.MWHEADER;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.dto.TranrqData;
import com.exam.exam.entity.CustomerEntity;
import com.exam.exam.exception.DuplicateDataException;
import com.exam.exam.repo.CustomerRepo;
import com.exam.exam.service.CustomerService;

import tools.jackson.databind.ObjectMapper;

@Service
public class ServiceImpl implements CustomerService {
    @Autowired
    private ObjectMapper om;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerResponse<T001Tranrs> createStore(CustomerRequest<T001Tranrq> customerRequest)
            throws DuplicateDataException {
        T001Tranrq tranrq = customerRequest.getTranrq();
        TranrqData dataDto = tranrq.getData();
        if (customerRepo.existsByIdNum(dataDto.getIdNum())) {
            throw new DuplicateDataException();
        }
        CustomerEntity customerEntity = om.convertValue(dataDto, CustomerEntity.class);
        customerRepo.save(customerEntity);
        // 準備回應訊息
        T001Tranrs createTranrs = new T001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // Response
        CustomerResponse<T001Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }
}
