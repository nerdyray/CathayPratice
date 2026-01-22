package com.store.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.store.demo.dto.Q001Tranrq;
import com.store.demo.dto.Q001Tranrs;

import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.T002Tranrq;
import com.store.demo.dto.T002Tranrs;
import com.store.demo.exception.DataNotFoundException;
import com.store.demo.exception.ErrorInputException;

public interface StoreService {
    StoreResponse<T002Tranrs> createStore(StoreRequest<T002Tranrq> storeRequest)
            throws ErrorInputException;

    StoreResponse<Q001Tranrs> findAllStore(StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException;
}