package com.store.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @RequestMapping("/store")：指定這個 Controller 處理的所有請求路徑都在 "/store" 之下。
 * @RestController：這是一個方便的註解，它結合了 @Controller 和 @ResponseBody。
 *                 表示這個類別中的所有方法都會回傳 JSON 格式的資料，而不是視圖（例如 HTML 頁面）。
 * @CrossOrigin(origins = "*")：允許來自任何來源的跨域請求。這在前後端分離的架構中很常見。
 */
@RequestMapping("/store")
@RestController
@CrossOrigin(origins = "*")
public class StoreController {

    // @Autowired：自動注入 StoreService 的實例，讓 Controller 可以使用 Service 層的功能。
    @Autowired
    StoreService storeService;

    /**
     * @PostMapping("/create")：將 HTTP POST 請求映射到 /store/create 路徑。
     * 用於新增店家資料。
     * @param storeRequest 包含店家資料的請求物件。
     * @param err 用於接收驗證錯誤。
     * @return 回傳一個包含操作結果的回應物件。
     * @throws ErrorInputException 如果輸入資料有誤，拋出此例外。
     */
    @PostMapping("/create")
    public StoreResponse<T002Tranrs> createStore(@RequestBody StoreRequest<T002Tranrq> storeRequest,Errors err)
            throws ErrorInputException{
                if(err.hasErrors()){
                    throw new ErrorInputException();
                }
        return storeService.createStore(storeRequest);
    }

    /**
     * @PostMapping("/query")：將 HTTP POST 請求映射到 /store/query 路徑。
     * 用於查詢店家資料。
     * @param storeRequest 包含查詢條件的請求物件。
     * @return 回傳一個包含查詢結果的回應物件。
     * @throws ErrorInputException 如果輸入資料有誤，拋出此例外。
     * @throws DataNotFoundException 如果找不到資料，拋出此例外。
     */
    @PostMapping("/query")
    public StoreResponse<Q001Tranrs> queryStore(@RequestBody StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        return storeService.findAllStore(storeRequest);
    }

    /**
     * @PostMapping("/querystore")：將 HTTP POST 請求映射到 /store/querystore 路徑。
     * 用於根據店家 ID 查詢特定店家。
     * @param storeRequest 包含店家 ID 的請求物件。
     * @return 回傳一個包含查詢結果的回應物件。
     * @throws ErrorInputException 如果輸入資料有誤，拋出此例外。
     * @throws DataNotFoundException 如果找不到資料，拋出此例外。
     */
    @PostMapping("/querystore")
    public StoreResponse<Q001Tranrs> queryStoreById(@RequestBody StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        return storeService.findByStoreId(storeRequest);
    }

    /**
     * @PostMapping("/maintain")：將 HTTP POST 請求映射到 /store/maintain 路徑。
     * 用於更新店家資料。
     * @param storeRequest 包含要更新的店家資料的請求物件。
     * @return 回傳一個包含操作結果的回應物件。
     * @throws ErrorInputException 如果輸入資料有誤，拋出此例外。
     * @throws DataNotFoundException 如果找不到要更新的資料，拋出此例外。
     */
    @PostMapping("/maintain")
    public StoreResponse<T001Tranrs> updateStore(@RequestBody StoreRequest<T001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        return storeService.updateStore(storeRequest);
    }

    /**
     * @PostMapping("/delete")：將 HTTP POST 請求映射到 /store/delete 路徑。
     * 用於刪除店家資料。
     * @param storeRequest 包含要刪除的店家 ID 的請求物件。
     * @return 回傳一個包含操作結果的回應物件。
     * @throws DataNotFoundException 如果找不到要刪除的資料，拋出此例外。
     */
    @PostMapping("/delete")
    public StoreResponse<T003Tranrs> deleteStore(@RequestBody StoreRequest<T003Tranrq> storeRequest)
            throws DataNotFoundException {
        return storeService.deleteStore(storeRequest);
    }

}
