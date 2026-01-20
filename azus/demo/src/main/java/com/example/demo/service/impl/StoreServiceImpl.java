package com.example.demo.service.impl;


import org.springframework.stereotype.Service;

import com.example.demo.common.UserObject;
import com.example.demo.service.StoreService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
private final UserObject userObject;
}
