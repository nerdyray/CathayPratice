package com.store.demo.controller.advice;

import java.io.Serializable;
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
 * @ControllerAdvice：這是一個全域的例外處理器。
 *                                 它可以捕捉整個應用程式中由 @Controller 或 @RestController
 *                                 拋出的例外。
 */
@ControllerAdvice
public class WebExceptionHandler implements Serializable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");

    /**
     * @ExceptionHandler(ErrorInputException.class)：指定這個方法專門處理 ErrorInputException
     *                                                         例外。
     * @ResponseBody：表示這個方法的回傳值會被轉換成 JSON 格式。
     * @return 回傳一個標準的錯誤回應物件。
     */
    @ResponseBody
    @ExceptionHandler(ErrorInputException.class)
    public StoreResponse handleErrroInputException() {
        // 建立一個空的 Tranrs 物件
        T002Tranrs createTranrs = new T002Tranrs();
        // 建立一個 MwHeader，並設定錯誤訊息
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E0001");
        createMwHeader.setReturndesc("必填欄位不完整");
        // 組合完整的回應物件
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * @ExceptionHandler(MethodArgumentTypeMismatchException.class)：處理當傳入參數型別不符時的例外。
     *                                                                               例如，需要的是數字，但傳入的是字串。
     *                                                                               @return
     *                                                                               回傳一個標準的錯誤回應物件。
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public StoreResponse handleMethodArgumentTypeMismatchException() {
        // 建立一個空的 Tranrs 物件
        T002Tranrs createTranrs = new T002Tranrs();
        // 建立一個 MwHeader，並設定錯誤訊息
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E0001");
        createMwHeader.setReturndesc("必填欄位不完整");
        // 組合完整的回應物件
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * 查無資料的例外處理
     * 
     * @return
     */
    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    public StoreResponse DataNotFoundException() {
        // 建立一個空的 Tranrs 物件
        T002Tranrs createTranrs = new T002Tranrs();
        // 建立一個 MwHeader，並設定錯誤訊息
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E702");
        createMwHeader.setReturndesc("查無資料");
        // 組合完整的回應物件
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * @ExceptionHandler(UpdateFailedException.class)：處理更新失敗時拋出的自訂例外。
     *                                                                @param ex
     *                                                                捕捉到的例外物件。
     * @return 回傳一個標準的錯誤回應物件。
     */
    @ResponseBody
    @ExceptionHandler(UpdateFailedException.class)
    public StoreResponse handleDataNotFoundException(UpdateFailedException ex) {
        // 建立一個空的 Tranrs 物件
        T002Tranrs createTranrs = new T002Tranrs();
        // 建立一個 MwHeader，並設定錯誤訊息
        MwHeader createMwHeader = new MwHeader();
        createMwHeader.setMsgid("XXA-C-STORE002");
        createMwHeader.setReturncode("E002");
        createMwHeader.setReturndesc("更新失敗");
        // 組合完整的回應物件
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwHeader);
        res.setTranrs(createTranrs);
        return res;
    }

}