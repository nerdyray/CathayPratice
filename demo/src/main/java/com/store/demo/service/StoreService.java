package com.store.demo.service;

import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.XXACSTORET002Tranrq;
import com.store.demo.dto.XXACSTORET002Tranrs;
import com.store.demo.exception.ErrorInputException;

public interface StoreService {
    StoreResponse<XXACSTORET002Tranrs> createStore(StoreRequest<XXACSTORET002Tranrq> storeRequest)
            throws ErrorInputException;
}