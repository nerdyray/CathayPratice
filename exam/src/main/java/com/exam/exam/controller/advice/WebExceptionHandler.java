package com.exam.exam.controller.advice;

import java.io.Serial;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.MWHEADER;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.exception.DataNotFoundException;
import com.exam.exam.exception.DeleteFailedException;
import com.exam.exam.exception.DuplicateDataException;
import com.exam.exam.exception.ErrorInputException;
import com.exam.exam.exception.UpdateFailedException;

@ControllerAdvice
public class WebExceptionHandler implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
    @Serial
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @ExceptionHandler(ErrorInputException.class)
    public CustomerResponse handleErrorInputException() {
        T001Tranrs createTranrs = new T001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT001");
        createMwheader.setReturncode("E001");
        createMwheader.setReturndesc("必填欄位不完整");

        CustomerResponse<T001Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    public CustomerResponse handleDataNotFoundException() {
        T001Tranrs createTranrs = new T001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT001");
        createMwheader.setReturncode("E702");
        createMwheader.setReturndesc("找不到資料");

        CustomerResponse<T001Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(UpdateFailedException.class)
    public CustomerResponse handleUpdateFailedException() {
        T001Tranrs createTranrs = new T001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT001");
        createMwheader.setReturncode("E002");
        createMwheader.setReturndesc("更新失敗");

        CustomerResponse<T001Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(DuplicateDataException.class)
    public CustomerResponse handleDuplicateDataException() {
        T001Tranrs createTranrs = new T001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT001");
        createMwheader.setReturncode("E003");
        createMwheader.setReturndesc("資料重複");

        CustomerResponse<T001Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    @ResponseBody
    @ExceptionHandler(DeleteFailedException.class)
    public CustomerResponse handleDeleteFailedException() {
        T001Tranrs createTranrs = new T001Tranrs();
        MWHEADER createMwheader = new MWHEADER();
        createMwheader.setMsgid("XXA-C-CIFT003");
        createMwheader.setReturncode("E004");
        createMwheader.setReturndesc("刪除失敗");

        CustomerResponse<T001Tranrs> res = new CustomerResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }
}
