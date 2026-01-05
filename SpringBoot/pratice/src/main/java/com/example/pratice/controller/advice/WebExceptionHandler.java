package com.example.pratice.controller.advice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.pratice.dto.CUSTT001Tranrs;
import com.example.pratice.dto.CustomerResponse;
import com.example.pratice.dto.PrHeader;
import com.example.pratice.exception.DataNotFoundException;
import com.example.pratice.exception.ErrorInputException;

/**
 * 例外處理
 */
@ControllerAdvice
public class WebExceptionHandler implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");

    @ResponseBody
    @ExceptionHandler(ErrorInputException.class)
    // 錯誤輸入例外拋出
    public CustomerResponse handleErrroInputException() {
        // 建立PrHeader
        PrHeader createHeader = new PrHeader();
        // PrHeader統一SID
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        // CustomerTT001的Tranrs
        CUSTT001Tranrs createTranrs = new CUSTT001Tranrs();
        createTranrs.setMessage("輸入錯誤");
        CustomerResponse<CUSTT001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    // 輸入型別錯誤
    public CustomerResponse handleMethodArgumentTypeMismatchException() {
        // 建立PrHeader
        PrHeader createHeader = new PrHeader();
        // PrHeader統一SID
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        // CustomerTT001的Tranrs
        CUSTT001Tranrs createTranrs = new CUSTT001Tranrs();
        createTranrs.setMessage("型別錯誤");
        CustomerResponse<CUSTT001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    // 查無資料例外拋出
    public CustomerResponse handleDataNotFoundException(DataNotFoundException ex) {
        // 建立PrHeader
        PrHeader createHeader = new PrHeader();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
        // PrHeader統一SID
        createHeader.setSid(Long.parseLong(formatter.format(LocalDateTime.now())));
        // CustomerTT001的Tranrs
        CUSTT001Tranrs createTranrs = new CUSTT001Tranrs();
        createTranrs.setMessage("找不到符合資料" + ex);
        CustomerResponse<CUSTT001Tranrs> res = new CustomerResponse<>();
        res.setPrHeader(createHeader);
        res.setTranrs(createTranrs);
        return res;
    }

}
