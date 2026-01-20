package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.StoreService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.StoreRequest;
import com.example.demo.dto.StoreResponse;
import com.example.demo.dto.XXACSTORET002Tranrq;
import com.example.demo.dto.XXACSTORET002Tranrs;

@RequestMapping("/store")
@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("/create")
    public StoreResponse<XXACSTORET002Tranrs>createStore(@RequestBody StoreRequest<XXACSTORET002Tranrq> storeRequest) {
        return storeService.createStore(storeRequest);
    }

}
