package com.exam.exam.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.exam.exam.dto.CriteriaDTO;
import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.MWHEADER;
import com.exam.exam.dto.Q001Tranrq;
import com.exam.exam.dto.Q001Tranrs;
import com.exam.exam.dto.Q002Tranrq;
import com.exam.exam.dto.Q002TranrqSortInfo;
import com.exam.exam.dto.Q002Tranrs;
import com.exam.exam.dto.Q002TranrsItems;
import com.exam.exam.dto.Q003Tranrq;
import com.exam.exam.dto.Q003Tranrs;
import com.exam.exam.dto.Q004Tranrq;
import com.exam.exam.dto.Q004Tranrs;
import com.exam.exam.dto.Q004TranrsEducation;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.dto.T002Tranrq;
import com.exam.exam.dto.T002Tranrs;
import com.exam.exam.dto.T003Tranrq;
import com.exam.exam.dto.T003Tranrs;
import com.exam.exam.dto.TranData;
import com.exam.exam.entity.CommCodeEntity;
import com.exam.exam.entity.CustomerEntity;
import com.exam.exam.exception.DataNotFoundException;
import com.exam.exam.exception.DeleteFailedException;
import com.exam.exam.exception.DuplicateDataException;
import com.exam.exam.repo.CommomCodeRepo;
import com.exam.exam.repo.CustomerRepo;
import com.exam.exam.service.CustomerService;
import com.exam.exam.specification.CustomerCriteria;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;

/**
 * 客戶服務的實作，處理客戶資料的 CRUD 操作。
 * 所有方法都在交易中執行。
 */
@Transactional
@Service
public class ServiceImpl implements CustomerService {
    @Autowired
    private ObjectMapper om;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CommomCodeRepo commomCodeRepo;

    /**
     * {@inheritDoc}
     * 建立新客戶。如果身分證號碼已存在，則會拋出 {@link DuplicateDataException}。
     */
    @Override
    public CustomerResponse<T001Tranrs> createCustomer(CustomerRequest<T001Tranrq> customerRequest)
            throws DuplicateDataException {
        T001Tranrq tranrq = customerRequest.getTranrq();
        TranData dataDto = tranrq.getData();
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

    /**
     * {@inheritDoc}
     * 查詢所有客戶，並提供分頁功能。如果找不到任何資料，則拋出 {@link DataNotFoundException}。
     */
    @Override
    public CustomerResponse<Q002Tranrs> findAllCustomer(CustomerRequest<Q002Tranrq> customerRequest)
            throws DataNotFoundException {
        Q002Tranrq tranrq = customerRequest.getTranrq();
        // Build Specification
        CriteriaDTO criteriaDto = om.convertValue(tranrq.getTranrqData(), CriteriaDTO.class);
        Specification<CustomerEntity> cutomerSpecification = CustomerCriteria.buildSearchSpecification(criteriaDto);
        // Build Pagable
        int pageNumber = tranrq.getPage().getPageNumber();
        int pageSize = tranrq.getPage().getPageSize();
        int pageIndex = pageNumber;
        String sortByRaw = tranrq.getStoreInfo().getSortBy();
        String sortByClean = !StringUtils.isBlank(sortByRaw) ? sortByRaw.toUpperCase() : "ASC";
        Sort.Direction sortBy = "ASC".equals(sortByClean) ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        String sortByColumnRaw = tranrq.getStoreInfo().getSortColumn();
        String sortByColumnClean = !StringUtils.isBlank(sortByColumnRaw) ? sortByColumnRaw : "gender";
        Sort sort = Sort.by(sortBy, sortByColumnClean);
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<CustomerEntity> pageEntity = customerRepo.findAll(cutomerSpecification, pageable);
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
        createSortInfo.setSortBy(sortByClean);
        createSortInfo.setSortColumn(sortByColumnClean);
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

    /**
     * {@inheritDoc}
     * 根據訂單 ID 查找客戶。如果找不到客戶，則拋出 {@link DataNotFoundException}。
     */
    @Override
    public CustomerResponse<Q001Tranrs> findByOrderId(CustomerRequest<Q001Tranrq> customerRequest)
            throws DataNotFoundException {
        Q001Tranrq tranrq = customerRequest.getTranrq();
        Integer orderId = tranrq.getOrderId();

        CustomerEntity entity = customerRepo.findByOrderId(orderId)
                .orElseThrow(() -> new DataNotFoundException());

        TranData dataDto = om.convertValue(entity, TranData.class);
        List<TranData> dataList = new ArrayList<>();
        dataList.add(dataDto);

        Q001Tranrs createTranrs = new Q001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        // 組裝回應表頭
        createMwheader.setMsgid("XXA-C-CIFQ001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // 回應本體
        createTranrs.setTranData(dataList);
        // 裝進CustomerResponse
        CustomerResponse<Q001Tranrs> res = new CustomerResponse<Q001Tranrs>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * {@inheritDoc}
     * 根據身分證號碼檢查客戶是否存在。如果不存在，則拋出 {@link DataNotFoundException}。
     */
    @Override
    public CustomerResponse<Q003Tranrs> excisedByIdNum(CustomerRequest<Q003Tranrq> customerRequest)
            throws DataNotFoundException {
        Q003Tranrq tranrq = customerRequest.getTranrq();
        String idNum = tranrq.getIdNum();
        if (!customerRepo.existsByIdNum(tranrq.getIdNum())) {
            throw new DataNotFoundException();
        }
        Q003Tranrs createTranrs = new Q003Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        // 組裝回應表頭
        createMwheader.setMsgid("XXA-C-CIFQ003");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // 回應本體
        createTranrs.setIdNum(idNum);
        // 裝進CustomerResponse
        CustomerResponse<Q003Tranrs> res = new CustomerResponse<Q003Tranrs>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * {@inheritDoc}
     * 更新客戶資料。如果找不到指定的客戶，則拋出 {@link DataNotFoundException}。
     */
    @Override
    public CustomerResponse<T002Tranrs> updateCustomer(CustomerRequest<T002Tranrq> customerRequest)
            throws DataNotFoundException {
        T002Tranrq tranrq = customerRequest.getTranrq();
        TranData dataDto = tranrq.getData();
        Integer orderId = dataDto.getOrderId();
        CustomerEntity entity = customerRepo.findByOrderId(orderId)
                .orElseThrow(() -> new DataNotFoundException());
        entity = om.convertValue(dataDto, CustomerEntity.class);
        System.out.println(entity);
        // List<TranData> dataList = new ArrayList<>();
        // dataList.add(dataDto);
        // System.out.println(dataList);
        customerRepo.save(entity);

        T002Tranrs createTranrs = new T002Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT002");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // Response
        CustomerResponse<T002Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * {@inheritDoc}
     * 根據訂單 ID 刪除客戶。如果刪除失敗，則拋出 {@link DeleteFailedException}。
     */
    @Override
    public CustomerResponse<T003Tranrs> deleteCustomer(CustomerRequest<T003Tranrq> customerRequest)
            throws DeleteFailedException {
        T003Tranrq tranrq = customerRequest.getTranrq();
        Integer orderId = tranrq.getOrderId();
        customerRepo.deleteByOrderId(orderId)
                .orElseThrow(() -> new DeleteFailedException());
        T003Tranrs createTranrs = new T003Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        // 組裝回應表頭
        createMwheader.setMsgid("XXA-C-CIFT003");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // 裝進CustomerResponse
        CustomerResponse<T003Tranrs> res = new CustomerResponse<T003Tranrs>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public CustomerResponse<Q004Tranrs> selectOpt(CustomerRequest<Q004Tranrq> customerRequest) {
        List<CommCodeEntity> eduOptions = commomCodeRepo.findByMsgCode("Education");
        List<Q004TranrsEducation> eduDto = eduOptions.stream()
                .map(entity -> om.convertValue(entity, Q004TranrsEducation.class)).collect(Collectors.toList());
        Q004Tranrs createTranrs = new Q004Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        // 組裝回應表頭
        createMwheader.setMsgid("XXA-C-CIFQ004");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // CreateTranrs
        createTranrs.setEducation(eduDto);
        // 裝進CustomerResponse
        CustomerResponse<Q004Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;

    }
}
