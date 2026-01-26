package com.store.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.demo.dto.Q001Tranrq;
import com.store.demo.dto.Q001Tranrs;
import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.T001Tranrq;
import com.store.demo.dto.T001Tranrs;
import com.store.demo.dto.T002Tranrq;
import com.store.demo.dto.T002Tranrs;
import com.store.demo.dto.T003Tranrq;
import com.store.demo.dto.T003Tranrs;
import com.store.demo.exception.DataNotFoundException;
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
    public StoreResponse<T002Tranrs> createStore(@RequestBody StoreRequest<T002Tranrq> storeRequest)
            throws ErrorInputException {
        return storeService.createStore(storeRequest);
    }

    @PostMapping("/query")
    public StoreResponse<Q001Tranrs> queryStore(@RequestBody StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        return storeService.findAllStore(storeRequest);
    }

    @PostMapping("/querystore")
    public StoreResponse<Q001Tranrs> queryStoreById(@RequestBody StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        return storeService.findByStoreId(storeRequest);
    }

    @PostMapping("/maintain")
    public StoreResponse<T001Tranrs> updateStore(@RequestBody StoreRequest<T001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        return storeService.updateStore(storeRequest);
    }

    @PostMapping("/delete")
    public StoreResponse<T003Tranrs> deleteStore(@RequestBody StoreRequest<T003Tranrq> storeRequest)
            throws DataNotFoundException {
        return storeService.deleteStore(storeRequest);
    }

}
