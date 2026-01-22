package com.store.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.demo.common.UserObject;
import com.store.demo.dto.MwHeader;
import com.store.demo.dto.Q001TranrqPage;
import com.store.demo.dto.Q001Tranrq;
import com.store.demo.dto.Q001Tranrs;
import com.store.demo.dto.Q001TranrsItems;
import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.T002Tranrq;
import com.store.demo.dto.T002Tranrs;
import com.store.demo.entity.StoreEntity;
import com.store.demo.exception.DataNotFoundException;
import com.store.demo.exception.ErrorInputException;
import com.store.demo.repo.StoreRepo;
import com.store.demo.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Transactional
@Service

public class StoreServiceImpl implements StoreService {

    @Autowired
    private ObjectMapper om;
    @Autowired
    private StoreRepo storeRepo;
    // private final UserObject userObject;

    @Override
    public StoreResponse<T002Tranrs> createStore(StoreRequest<T002Tranrq> storeRequest)
            throws ErrorInputException {
        StoreEntity storeEntity;
        T002Tranrq data = storeRequest.getTranrq();
        if (storeRepo.existsByStoreId(data.getStoreId())) {
            throw new ErrorInputException();

        }
        storeEntity = StoreEntity.builder()
                .storeId(data.getStoreId())
                .storeName(data.getStoreName())
                .owner(data.getOwner())
                .tel(data.getTel())
                .fax(data.getFax())
                .mobile(data.getMobile())
                .address(data.getAddress())
                .evaluation(data.getEvaluation())
                .remarks(data.getRemarks())
                .build();
        storeRepo.save(storeEntity);
        T002Tranrs createTranrs = new T002Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORE002");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public StoreResponse<Q001Tranrs> findAllStore(StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        Page<StoreEntity> entityPage = storeRepo.findAllByStoreName();
        if (entityPage.isEmpty()) {
        }
        // 使用 map + convertValue
        // 組裝Q001TranrsItems
        List<StoreEntity> content = entityPage.getContent();
        Pageable page = entityPage.getPageable();
        List<Q001TranrsItems> items = content.stream()
                .map(entity -> om.convertValue(entity, Q001TranrsItems.class))
                .collect(Collectors.toList());
        // 組裝MwHeader
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORQ001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // 組裝PageDatas(分頁)
        Q001Tranrs createTranrs = new Q001Tranrs();
        createTranrs.setPageSize(page.getPageSize());
        createTranrs.setPageNumber(page.getPageNumber());
        createTranrs.setTotalPage(entityPage.getTotalPages());
        createTranrs.setTotalCount(entityPage.getTotalElements());
        StoreResponse<Q001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }
}