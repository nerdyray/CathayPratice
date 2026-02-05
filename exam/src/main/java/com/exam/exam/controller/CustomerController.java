package com.exam.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.Q001Tranrq;
import com.exam.exam.dto.Q001Tranrs;
import com.exam.exam.dto.Q002Tranrq;
import com.exam.exam.dto.Q002Tranrs;
import com.exam.exam.dto.Q003Tranrq;
import com.exam.exam.dto.Q003Tranrs;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.dto.T002Tranrq;
import com.exam.exam.dto.T002Tranrs;
import com.exam.exam.dto.T003Tranrq;
import com.exam.exam.dto.T003Tranrs;
import com.exam.exam.exception.DataNotFoundException;
import com.exam.exam.exception.DeleteFailedException;
import com.exam.exam.exception.DuplicateDataException;
import com.exam.exam.exception.ErrorInputException;
import com.exam.exam.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 客戶資料相關的 RESTful API 控制器。
 * 提供客戶資料的建立、查詢、更新和刪除功能。
 * 允許所有來源的跨域請求。
 */
@RequestMapping("/cif")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * 建立新客戶。
     *
     * @param customerRequest 包含客戶資料的請求。
     * @param err 綁定和驗證錯誤。
     * @return 包含交易結果的回應。
     * @throws ErrorInputException 如果輸入資料驗證失敗。
     * @throws DuplicateDataException 如果客戶資料已存在。
     */
    @PostMapping("create")
    public CustomerResponse<T001Tranrs> createCustomer(@Valid @RequestBody CustomerRequest<T001Tranrq> customerRequest,
            Errors err) throws ErrorInputException, DuplicateDataException {
        if (err.hasErrors()) {
            throw new ErrorInputException();
        }
        return customerService.createCustomer(customerRequest);
    }

    /**
     * 查詢所有客戶（分頁）。
     *
     * @param customerRequest 包含分頁資訊的請求。
     * @return 包含客戶列表和分頁資訊的回應。
     * @throws DataNotFoundException 如果找不到任何客戶資料。
     */
    @PostMapping("filter")
    public CustomerResponse<Q002Tranrs> findAllCustomer(
            @Valid @RequestBody CustomerRequest<Q002Tranrq> customerRequest) throws DataNotFoundException {
        return customerService.findAllCustomer(customerRequest);
    }

    /**
     * 依訂單 ID 查詢特定客戶。
     *
     * @param customerRequest 包含訂單 ID 的請求。
     * @return 包含客戶詳細資料的回應。
     * @throws DataNotFoundException 如果找不到該訂單 ID 的客戶。
     */
    @PostMapping("getOne")
    public CustomerResponse<Q001Tranrs> findByOrderId(
            @Valid @RequestBody CustomerRequest<Q001Tranrq> customerRequest) throws DataNotFoundException {
        return customerService.findByOrderId(customerRequest);
    }

    /**
     * 依身分證字號檢查客戶是否存在。
     *
     * @param customerRequest 包含身分證字號的請求。
     * @return 包含檢查結果的回應。
     * @throws DataNotFoundException 如果找不到該身分證字號的客戶。
     */
    @PostMapping("checkId")
    public CustomerResponse<Q003Tranrs> excisedByIdNum(
            @Valid @RequestBody CustomerRequest<Q003Tranrq> customerRequest) throws DataNotFoundException {
        return customerService.excisedByIdNum(customerRequest);
    }

    /**
     * 更新現有客戶資料。
     *
     * @param customerRequest 包含要更新的客戶資料的請求。
     * @param err 綁定和驗證錯誤。
     * @return 包含交易結果的回應。
     * @throws ErrorInputException 如果輸入資料驗證失敗。
     * @throws DataNotFoundException 如果找不到要更新的客戶。
     */
    @PostMapping("editInfo")
    public CustomerResponse<T002Tranrs> updateCustomer(@Valid @RequestBody CustomerRequest<T002Tranrq> customerRequest,
            Errors err) throws ErrorInputException, DataNotFoundException {
        if (err.hasErrors()) {
            throw new ErrorInputException();
        }
        return customerService.updateCustomer(customerRequest);
    }

    /**
     * 依訂單 ID 刪除客戶。
     *
     * @param customerRequest 包含訂單 ID 的請求。
     * @param err 綁定和驗證錯誤。
     * @return 包含交易結果的回應。
     * @throws DataNotFoundException 如果找不到要刪除的客戶。
     * @throws DeleteFailedException 如果刪除操作失敗。
     */
    @PostMapping("deleteInfo")
    public CustomerResponse<T003Tranrs> deleteCustomer(@Valid @RequestBody CustomerRequest<T003Tranrq> customerRequest,
            Errors err) throws DataNotFoundException, DeleteFailedException {
        if (err.hasErrors()) {
            throw new DeleteFailedException();
        }
        return customerService.deleteCustomer(customerRequest);
    }
}