package com.store.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.XXACSTORET002Tranrq;
import com.store.demo.dto.XXACSTORET002Tranrs;
import com.store.demo.exception.ErrorInputException;
import com.store.demo.service.StoreService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/store")
@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("/create")
    public StoreResponse<XXACSTORET002Tranrs> createStore(@RequestBody StoreRequest<XXACSTORET002Tranrq> storeRequest)
            throws ErrorInputException {
        return storeService.createStore(storeRequest);
    }

}