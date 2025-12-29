package com.example.pratice.svc.svcImpl;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pratice.dto.CUSTQ001Tranrs;
import com.example.pratice.dto.CUSTT001Tranrq;
import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.dto.PrHeader;
import com.example.pratice.entity.CustomerEntity;
import com.example.pratice.repository.CustomerRepo;
import com.example.pratice.svc.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerResponse<CUSTT001Tranrs> createCustomer(CustomerRequest<CUSTT001Tranrq> customerRequest) {
        LocalDate date = LocalDate.now();
        CustomerEntity customerEntity;
        CUSTT001Tranrq data = customerRequest.getTranrq();
        // 檢核各項輸入
        // if (!customerRepo.existsByCustmoterId(data.getCustomerId())) {
        //     throw new InputMismatchException("客戶資料已存在");
        // }
            // 主要操作
            customerEntity = CustomerEntity.builder()
                    .custmoterId(data.getCustomerId())
                    .name(data.getName())
                    .birthday(data.getBirthday())
                    .sex(data.getSex())
                    .id(data.getId())
                    .build();
            customerRepo.save(customerEntity);
            // 建封包由內而外層層回傳
            CUSTT001Tranrs createTranrs = new CUSTT001Tranrs();
            createTranrs.setMessage("Success");
            PrHeader createHeader = new PrHeader();
            //生成SID(當前時間)
            createHeader.setSid(2134154567L);
            CustomerResponse<CUSTT001Tranrs> res = new CustomerResponse<>();
            res.setPrHeader(createHeader);
            res.setTranrs(createTranrs);
            return res;
        

    }

    @Override
    public List<CustomerEntity> searchCustomer(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchCustomer'");
    }

}
