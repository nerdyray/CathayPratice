package com.example.demo.service.impl;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.common.UserObject;
import com.example.demo.dto.MwHeader;
import com.example.demo.dto.StoreRequest;
import com.example.demo.dto.StoreResponse;
import com.example.demo.dto.XXACSTORET002Tranrq;
import com.example.demo.dto.XXACSTORET002Tranrs;
import com.example.demo.entity.StoreEntity;
import com.example.demo.repo.StoreRepo;
import com.example.demo.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Transactional
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    @Autowired
    private final StoreRepo storeRepo;
private final UserObject userObject;
@Override
public StoreResponse<XXACSTORET002Tranrs> createStore(StoreRequest<XXACSTORET002Tranrq> storeRequest) {
    StoreEntity storeEntity;
    XXACSTORET002Tranrq data=storeRequest.getTranrq();
    if(storeRepo.existsByStoreId(data.getStoreId())){
        
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
    XXACSTORET002Tranrs createTranrs= new XXACSTORET002Tranrs();
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
