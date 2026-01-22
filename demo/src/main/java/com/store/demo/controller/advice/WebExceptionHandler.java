package com.store.demo.controller.advice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.store.demo.dto.MwHeader;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.T002Tranrs;
import com.store.demo.exception.DataNotFoundException;
import com.store.demo.exception.ErrorInputException;
import com.store.demo.exception.UpdateFailedException;

/**
 * 例外處理
 */
@ControllerAdvice
public class WebExceptionHandler implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");

    @ResponseBody
    @ExceptionHandler(ErrorInputException.class)
    // 錯誤輸入例外拋出
    public StoreResponse handleErrroInputException() {
        // 建立MwHeader
        // StoreTT001的Tranrs
        T002Tranrs createTranrs = new T002Tranrs();
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E0001");
        createMwHeader.setReturndesc("必填欄位不完整");
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    // 輸入型別錯誤
    public StoreResponse handleMethodArgumentTypeMismatchException() {
        // 建立PrHeader
        T002Tranrs createTranrs = new T002Tranrs();
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E0001");
        createMwHeader.setReturndesc("必填欄位不完整");
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(UpdateFailedException.class)
    // 查無資料例外拋出
    public StoreResponse handleDataNotFoundException(UpdateFailedException ex) {
        T002Tranrs createTranrs = new T002Tranrs();
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E002");
        createMwHeader.setReturndesc("更新失敗");
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

}