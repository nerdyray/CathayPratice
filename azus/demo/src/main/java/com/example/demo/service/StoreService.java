package com.example.demo.service;

import com.example.demo.dto.StoreRequest;
import com.example.demo.dto.StoreResponse;
import com.example.demo.dto.XXACSTORET002Tranrq;
import com.example.demo.dto.XXACSTORET002Tranrs;
import com.example.demo.exception.ErrorInputException;

public interface  StoreService {
    StoreResponse<XXACSTORET002Tranrs> createStore(StoreRequest<XXACSTORET002Tranrq>storeRequest)throws ErrorInputException;
}
