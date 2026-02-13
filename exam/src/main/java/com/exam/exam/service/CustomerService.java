package com.exam.exam.service;

import com.exam.exam.dto.CustomerRequest;
import com.exam.exam.dto.CustomerResponse;
import com.exam.exam.dto.Q001Tranrq;
import com.exam.exam.dto.Q001Tranrs;
import com.exam.exam.dto.Q002Tranrq;
import com.exam.exam.dto.Q002Tranrs;
import com.exam.exam.dto.Q003Tranrq;
import com.exam.exam.dto.Q003Tranrs;
import com.exam.exam.dto.Q004Tranrq;
import com.exam.exam.dto.Q004Tranrs;
import com.exam.exam.dto.T001Tranrq;
import com.exam.exam.dto.T001Tranrs;
import com.exam.exam.dto.T002Tranrq;
import com.exam.exam.dto.T002Tranrs;
import com.exam.exam.dto.T003Tranrq;
import com.exam.exam.dto.T003Tranrs;
import com.exam.exam.exception.DataNotFoundException;
import com.exam.exam.exception.DeleteFailedException;
import com.exam.exam.exception.DuplicateDataException;

/**
 * 客戶服務介面，定義客戶相關操作的合約。
 */
public interface CustomerService {
        /**
         * 建立新客戶。
         *
         * @param customerRequest 包含客戶資料的請求。
         * @return 包含交易結果的回應。
         * @throws DuplicateDataException 如果客戶資料已存在。
         */
        CustomerResponse<T001Tranrs> createCustomer(CustomerRequest<T001Tranrq> customerRequest)
                        throws DuplicateDataException;

        /**
         * 查詢所有客戶（分頁）。
         *
         * @param customerRequest 包含分頁資訊的請求。
         * @return 包含客戶列表和分頁資訊的回應。
         * @throws DataNotFoundException 如果找不到任何客戶資料。
         */
        CustomerResponse<Q002Tranrs> findAllCustomer(CustomerRequest<Q002Tranrq> customerRequest)
                        throws DataNotFoundException;

        /**
         * 依訂單 ID 查詢特定客戶。
         *
         * @param customerRequest 包含訂單 ID 的請求。
         * @return 包含客戶詳細資料的回應。
         * @throws DataNotFoundException 如果找不到該訂單 ID 的客戶。
         */
        CustomerResponse<Q001Tranrs> findByOrderId(CustomerRequest<Q001Tranrq> customerRequest)
                        throws DataNotFoundException;

        /**
         * 依身分證字號檢查客戶是否存在。
         *
         * @param customerRequest 包含身分證字號的請求。
         * @return 包含檢查結果的回應。
         * @throws DataNotFoundException 如果找不到該身分證字號的客戶。
         */
        CustomerResponse<Q003Tranrs> excisedByIdNum(CustomerRequest<Q003Tranrq> customerRequest)
                        throws DataNotFoundException;

        /**
         * 更新現有客戶資料。
         *
         * @param customerRequest 包含要更新的客戶資料的請求。
         * @return 包含交易結果的回應。
         * @throws DataNotFoundException 如果找不到要更新的客戶。
         */
        CustomerResponse<T002Tranrs> updateCustomer(CustomerRequest<T002Tranrq> customerRequest)
                        throws DataNotFoundException;

        /**
         * 依訂單 ID 刪除客戶。
         *
         * @param customerRequest 包含訂單 ID 的請求。
         * @return 包含交易結果的回應。
         * @throws DeleteFailedException 如果刪除失敗。
         */
        CustomerResponse<T003Tranrs> deleteCustomer(CustomerRequest<T003Tranrq> customerRequest)
                        throws DeleteFailedException;

        CustomerResponse<Q004Tranrs> selectOpt(CustomerRequest<Q004Tranrq> customerRequest);
}
