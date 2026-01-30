package com.store.demo.service;

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

/**
 * Service (服務層) 介面:
 * 定義了業務邏輯的契約 (Contract)。
 * 它只定義「要做什麼」，而不關心「怎麼做」。
 * 具體的實作會由 Impl (Implementation) 類別來完成 (例如 StoreServiceImpl)。
 * 這種方式有助於降低耦合度，讓系統更容易維護和測試。
 */
public interface StoreService {
        /**
         * 新增店家
         * @param storeRequest 包含店家資料的請求
         * @return 包含操作結果的回應
         * @throws ErrorInputException 如果輸入資料有誤
         */
        StoreResponse<T002Tranrs> createStore(StoreRequest<T002Tranrq> storeRequest)
                        throws ErrorInputException;

        /**
         * 查詢所有店家 (分頁)
         * @param storeRequest 包含查詢條件和分頁資訊的請求
         * @return 包含查詢結果的回應
         * @throws ErrorInputException 如果輸入資料有誤
         * @throws DataNotFoundException 如果查無資料
         */
        StoreResponse<Q001Tranrs> findAllStore(StoreRequest<Q001Tranrq> storeRequest)
                        throws ErrorInputException, DataNotFoundException;

        /**
         * 根據 ID 查詢特定店家
         * @param storeRequest 包含店家 ID 的請求
         * @return 包含店家資料的回應
         * @throws ErrorInputException 如果輸入資料有誤
         * @throws DataNotFoundException 如果查無資料
         */
        StoreResponse<Q001Tranrs> findByStoreId(StoreRequest<Q001Tranrq> storeRequest)
                        throws ErrorInputException, DataNotFoundException;

        /**
         * 更新店家資料
         * @param storeRequest 包含要更新的店家資料的請求
         * @return 包含操作結果的回應
         * @throws ErrorInputException 如果輸入資料有誤
         * @throws DataNotFoundException 如果找不到要更新的店家
         */
        StoreResponse<T001Tranrs> updateStore(StoreRequest<T001Tranrq> storeRequest)
                        throws ErrorInputException, DataNotFoundException;

        /**
         * 刪除店家
         * @param storeRequest 包含要刪除的店家 ID 的請求
         * @return 包含操作結果的回應
         * @throws DataNotFoundException 如果找不到要刪除的店家
         */
        StoreResponse<T003Tranrs> deleteStore(StoreRequest<T003Tranrq> storeRequest)
                        throws DataNotFoundException;
}