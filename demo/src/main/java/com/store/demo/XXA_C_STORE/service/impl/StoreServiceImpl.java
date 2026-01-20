package com.store.demo.XXA_C_STORE.service.impl;

import org.springframework.stereotype.Service;

import com.store.demo.XXA_C_STORE.common.UserObject;
import com.store.demo.XXA_C_STORE.service.StoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final UserObject userObjec;
}
