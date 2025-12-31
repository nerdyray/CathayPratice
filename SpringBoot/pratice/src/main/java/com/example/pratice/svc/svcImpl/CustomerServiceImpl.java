package com.example.pratice.svc.svcImpl;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pratice.dto.CUSTQ001Tranrs;
import com.example.pratice.dto.CUSTQ001TranrsDatas;
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
        String bankNum = "11111";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + bankNum;

        CustomerEntity customerEntity;
        CUSTT001Tranrq data = customerRequest.getTranrq();
        // 檢核各項輸入
        if (customerRepo.existsByCustomerId(data.getCustomerId())) {
            throw new InputMismatchException("客戶資料已存在");
        }
        // 主要操作
        customerEntity = CustomerEntity.builder()
                .customerId(data.getCustomerId())
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
        /**
         * 新增查詢修改方法介面 CustomerId要以localDate+DateFormmater格式化
         */
        // 生成SID(當前時間)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        CustomerResponse<CUSTT001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;

    }

    @Override
    public CustomerResponse<CUSTQ001Tranrs> searchCustomer(String id) {
        // 使用Optional檢查NPE
        CustomerEntity customerEntity = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("查無此客戶編號: " + id));
        // 進入組裝DTO
        CUSTQ001TranrsDatas data = new CUSTQ001TranrsDatas();
        data.setCustomerId(customerEntity.getCustomerId());
        data.setBirthday(customerEntity.getBirthday());
        data.setId(customerEntity.getId());
        data.setSex(customerEntity.getSex());
        data.setName(customerEntity.getName());
        List<CUSTQ001TranrsDatas> datasList = List.of(data);

        // 組裝CUSTQ001Tranrs
        CUSTQ001Tranrs createTranrs = new CUSTQ001Tranrs();
        createTranrs.setMessage("Success");
        createTranrs.setDatas(datasList);
        // 組裝Header
        PrHeader createHeader = new PrHeader();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        CustomerResponse<CUSTQ001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;

    }

}
