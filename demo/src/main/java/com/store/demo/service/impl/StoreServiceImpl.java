package com.store.demo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.demo.dto.MwHeader;

import com.store.demo.dto.Q001Tranrq;
import com.store.demo.dto.Q001Tranrs;
import com.store.demo.dto.Q001TranrsItems;

import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.T001Tranrq;
import com.store.demo.dto.T001Tranrs;
import com.store.demo.dto.T002Tranrq;
import com.store.demo.dto.T002Tranrs;
import com.store.demo.dto.T003Tranrq;
import com.store.demo.dto.T003Tranrs;
import com.store.demo.entity.StoreEntity;
import com.store.demo.exception.DataNotFoundException;
import com.store.demo.exception.ErrorInputException;
import com.store.demo.repo.StoreRepo;
import com.store.demo.service.StoreService;

import jakarta.transaction.Transactional;

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
        Q001Tranrq data = storeRequest.getTranrq();
        int pageNumber = data.getPage().getPageNumber();
        int pageSize = data.getPage().getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        // 決定要用哪種查詢 (解決 null 查詢條件問題)
        String searchName = data.getStoreName(); // 從 DTO 拿名字
        Page<StoreEntity> entityPage = storeRepo.findAllByStoreNameContaining(searchName, pageable);
        if (entityPage.isEmpty()) {
            throw new DataNotFoundException();
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
        createTranrs.setItems(items);
        StoreResponse<Q001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public StoreResponse<Q001Tranrs> findByStoreId(StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {

        Q001Tranrq data = storeRequest.getTranrq();
        Integer storeId = data.getStoreId();

        // 決定要用哪種查詢 (解決 null 查詢條件問題)
        StoreEntity storeEntity = storeRepo.findByStoreId(storeId)
                .orElseThrow(() -> new DataNotFoundException());
        Q001TranrsItems itemDto = om.convertValue(storeEntity, Q001TranrsItems.class);
        // 組裝Q001TranrsItems
        List<Q001TranrsItems> items = new ArrayList<>();
        items.add(itemDto);
        // 組裝MwHeader
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORQ001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        // 組裝PageDatas(分頁)
        Q001Tranrs createTranrs = new Q001Tranrs();
        createTranrs.setPageSize(0);
        createTranrs.setPageNumber(0);
        createTranrs.setTotalPage(0);
        createTranrs.setTotalCount(null);
        createTranrs.setItems(items);
        StoreResponse<Q001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public StoreResponse<T001Tranrs> updateStore(StoreRequest<T001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {

        T001Tranrq data = storeRequest.getTranrq();
        StoreEntity storeEntity = storeRepo.findByStoreId(data.getStoreId())
                .orElseThrow(() -> new DataNotFoundException());
        // 有找到就更新
        storeEntity.setStoreName(data.getStoreName());
        storeEntity.setOwner(data.getOwner());
        storeEntity.setTel(data.getTel());
        storeEntity.setFax(data.getFax());
        storeEntity.setMobile(data.getMobile());
        storeEntity.setAddress(data.getAddress());
        storeEntity.setEvaluation(data.getEvaluation());
        storeEntity.setRemarks(data.getRemarks());
        storeEntity.setUpdateTime(LocalDateTime.now());
        storeRepo.save(storeEntity);
        T001Tranrs createTranrs = new T001Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORET001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        StoreResponse<T001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @Override
    public StoreResponse<T003Tranrs> deleteStore(StoreRequest<T003Tranrq> storeRequest)
            throws DataNotFoundException {
        T003Tranrq data = storeRequest.getTranrq();
        StoreEntity storeEntity = storeRepo.deleteByStoreId(data.getStoreId())
                .orElseThrow(() -> new DataNotFoundException());
        T003Tranrs createTranrs = new T003Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORET003");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        StoreResponse<T003Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;

    }
}
