package com.exam.exam.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.MWHEADER;
import com.exam.exam.dto.Q002Tranrq;
import com.exam.exam.dto.Q002TranrqSortInfo;
import com.exam.exam.dto.Q002Tranrs;
import com.exam.exam.dto.Q002TranrsItems;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.dto.TranrqData;
import com.exam.exam.entity.CustomerEntity;
import com.exam.exam.exception.DataNotFoundException;
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
    public CustomerResponse<T001Tranrs> createCusotmer(CustomerRequest<T001Tranrq> customerRequest)
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

    @Override
    public CustomerResponse<Q002Tranrs> findAllCustomer(CustomerRequest<Q002Tranrq> customerRequest)
            throws DataNotFoundException {
        Q002Tranrq tranrq = customerRequest.getTranrq();
        int pageNumber = tranrq.getPage().getPageNumber();
        int pageSize = tranrq.getPage().getPageSize();
        int pageIndex = (pageNumber > 0) ? pageNumber - 1 : 0;
        Sort sort = Sort.by(Sort.Direction.DESC, "orderId");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<CustomerEntity> pageEntity = customerRepo.findAll(pageable);
        if (pageEntity.isEmpty()) {
            throw new DataNotFoundException();
        }

        List<Q002TranrsItems> items = pageEntity.getContent().stream()
                .map(entity -> {
                    Q002TranrsItems item = om.convertValue(entity, Q002TranrsItems.class);
                    return item;
                }).collect(Collectors.toList());
        // 組裝回應
        Q002Tranrs createTranrs = new Q002Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        Q002TranrqSortInfo createSortInfo = new Q002TranrqSortInfo();
        // 組裝回應表頭
        createMwheader.setMsgid("XXA-C-CIFQ002");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // 排序
        createSortInfo.setSortBy("DESC");
        createSortInfo.setColumn("ORDER_ID");
        // 回應本體
        createTranrs.setPageSize(pageSize);
        createTranrs.setPageNumber(pageNumber);
        createTranrs.setTotalPage(pageEntity.getTotalPages());
        createTranrs.setTotalCount(pageEntity.getTotalElements());
        createTranrs.setSortInfo(createSortInfo);
        createTranrs.setItems(items);
        // 裝進CustomerResponse
        CustomerResponse<Q002Tranrs> res = new CustomerResponse<Q002Tranrs>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }
}
