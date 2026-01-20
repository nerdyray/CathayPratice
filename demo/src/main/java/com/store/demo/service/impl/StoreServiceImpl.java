package com.store.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.demo.common.UserObject;
import com.store.demo.dto.MwHeader;
import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.XXACSTORET002Tranrq;
import com.store.demo.dto.XXACSTORET002Tranrs;
import com.store.demo.entity.StoreEntity;
import com.store.demo.exception.ErrorInputException;
import com.store.demo.repo.StoreRepo;
import com.store.demo.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    @Autowired
    private final StoreRepo storeRepo;
    private final UserObject userObject;

    @Override
    public StoreResponse<XXACSTORET002Tranrs> createStore(StoreRequest<XXACSTORET002Tranrq> storeRequest)
            throws ErrorInputException {
        StoreEntity storeEntity;
        XXACSTORET002Tranrq data = storeRequest.getTranrq();
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
        XXACSTORET002Tranrs createTranrs = new XXACSTORET002Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORE002");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");
        StoreResponse<XXACSTORET002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }
}