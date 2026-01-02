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
import com.example.pratice.dto.CUSTT002Tranrq;
import com.example.pratice.dto.CUSTT002Tranrs;
import com.example.pratice.dto.CUSTT003Tranrs;
import com.example.pratice.dto.CustomerRequest;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.dto.PrHeader;
import com.example.pratice.entity.CustomerEntity;
import com.example.pratice.exception.DataNotFoundException;
import com.example.pratice.exception.ErrorInputException;
import com.example.pratice.repository.CustomerRepo;
import com.example.pratice.svc.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
    // private static final DateTimeFormatter birthdayFormatter =
    // DateTimeFormatter.ofPattern("yyyy-MM-ddHHmmSS");

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerResponse<CUSTT001Tranrs> createCustomer(CustomerRequest<CUSTT001Tranrq> customerRequest)
            throws ErrorInputException {
        String bankNum = "11111";
        // String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        // + bankNum;

        CustomerEntity customerEntity;
        CUSTT001Tranrq data = customerRequest.getTranrq();
        // 檢核輸入
        if (customerRepo.existsByCustomerId(data.getCustomerId())) {
            throw new ErrorInputException();
        }
        // 主要操作
        customerEntity = CustomerEntity.builder()
                .customerId(data.getCustomerId())
                .name(data.getName())
                .birthday(LocalDate.parse(data.getBirthday()))
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
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        CustomerResponse<CUSTT001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;

    }
    // *
    // 尋找CustomerID */

    @Override
    public CustomerResponse<CUSTQ001Tranrs> searchCustomer(Long customerId)
            throws ErrorInputException, DataNotFoundException {
        // 使用Optional檢查是否有對應ID
        CustomerEntity customerEntity = customerRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new DataNotFoundException());
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
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        CustomerResponse<CUSTQ001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public CustomerResponse<CUSTT002Tranrs> updateCustomer(CustomerRequest<CUSTT002Tranrq> customerRequest)
            throws ErrorInputException, DataNotFoundException {
        // 使用Optional檢查是否有對應ID
        CUSTT002Tranrq updateCustt002Tranrq = customerRequest.getTranrq();
        CustomerEntity customerEntity = customerRepo.findByCustomerId(updateCustt002Tranrq.getCustomerId())
                .orElseThrow(() -> new DataNotFoundException());
        // 有找到的話就更新資料
        customerEntity.setBirthday(LocalDate.parse(updateCustt002Tranrq.getBirthday()));
        customerEntity.setName(updateCustt002Tranrq.getName());
        customerEntity.setSex(updateCustt002Tranrq.getSex());
        customerEntity.setId(updateCustt002Tranrq.getId());
        customerRepo.save(customerEntity);
        // 建立封包
        PrHeader createHeader = new PrHeader();
        CUSTT002Tranrs createTranrs = new CUSTT002Tranrs();
        createTranrs.setMessage("Sucess");
        // 生成SID(當前時間)
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        CustomerResponse<CUSTT002Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public CustomerResponse<CUSTT003Tranrs> deleteCustomer(Long customerId)
            throws ErrorInputException, DataNotFoundException {
        System.out.println(customerId);
        // 先找到要刪除的CustomerId
        CustomerEntity customerEntity = customerRepo.findByCustomerId(customerId)
                .orElseThrow(() -> new DataNotFoundException());
        customerRepo.delete(customerEntity);
        // 組裝CUSTT003Tranrs
        CUSTT003Tranrs createTranrs = new CUSTT003Tranrs();
        createTranrs.setMessage("Success");
        // 組裝Header
        PrHeader createHeader = new PrHeader();
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        CustomerResponse<CUSTT003Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;
    }
}
