package com.store.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.store.demo.dto.MwHeader;
import com.store.demo.dto.Q001Tranrq;
import com.store.demo.dto.Q001Tranrs;
import com.store.demo.dto.Q001TranrsItems;
import com.store.demo.dto.StoreRequest;
import com.store.demo.dto.StoreResponse;
import com.store.demo.dto.T001Tranrq;
import com.store.demo.dto.T001Tranrs;
import com.store.demo.dto.T002Tranrq;
import com.store.demo.dto.T002Tranrs;
import com.store.demo.dto.T003Tranrq;
import com.store.demo.dto.T003Tranrs;
import com.store.demo.entity.StoreEntity;
import com.store.demo.exception.DataNotFoundException;
import com.store.demo.exception.ErrorInputException;
import com.store.demo.repo.StoreProjection;
import com.store.demo.repo.StoreRepo;
import com.store.demo.service.StoreService;

import jakarta.transaction.Transactional;
import tools.jackson.databind.ObjectMapper;

/**
 * @Transactional：標記在類別上，表示這個類別所有 public 方法都具有交易性。
 *                                如果方法執行成功，交易就提交；如果中途發生例外，交易就回滾。
 * @Service：告訴 Spring 框架，這是一個服務層的元件，應該被 Spring 容器管理。
 */
@Transactional
@Service
public class StoreServiceImpl implements StoreService {

    // 自動注入 ObjectMapper，它是一個用來處理 JSON 轉換的工具。
    @Autowired
    private ObjectMapper om;

    // 自動注入 StoreRepo，它是我們用來操作資料庫的介面。
    @Autowired
    private StoreRepo storeRepo;

    /**
     * 新增店家資料
     * 
     * @param storeRequest 包含店家資料的請求
     * @return 包含操作結果的回應
     * @throws ErrorInputException 如果輸入資料有誤
     */
    @Override
    public StoreResponse<T002Tranrs> createStore(StoreRequest<T002Tranrq> storeRequest)
            throws ErrorInputException {
        // 從請求中獲取交易資料
        T002Tranrq data = storeRequest.getTranrq();

        // 使用 ObjectMapper 將傳入的 DTO (data) 轉換成資料庫實體 (StoreEntity)
        StoreEntity storeEntity = om.convertValue(data, StoreEntity.class);

        // 將轉換後的實體存入資料庫
        storeRepo.save(storeEntity);

        // 準備成功的回應訊息
        T002Tranrs createTranrs = new T002Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORE002");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");

        // 組合完整的回應物件
        StoreResponse<T002Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * 查詢所有店家資料（分頁）
     * 
     * @param storeRequest 包含查詢條件與分頁資訊的請求
     * @return 包含查詢結果與分頁資訊的回應
     * @throws ErrorInputException   如果輸入資料有誤
     * @throws DataNotFoundException 如果查無資料
     */
    @Override
    public StoreResponse<Q001Tranrs> findAllStore(StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {
        // 從請求中獲取交易資料
        Q001Tranrq data = storeRequest.getTranrq();
        // 獲取分頁資訊
        int pageNumber = data.getPage().getPageNumber();
        int pageSize = data.getPage().getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // 獲取查詢條件：店家名稱
        String searchName = data.getStoreName();
        // 呼叫 repository 進行查詢，並取得分頁結果
        Page<StoreProjection> projectionPage = storeRepo.findStoresWithDetails(searchName, pageable);

        // 如果查詢結果是空的，就拋出「查無資料」的例外
        if (projectionPage.isEmpty()) {
            throw new DataNotFoundException();
        }

        // 將查詢到的資料 (StoreProjection) 轉換成要回傳的 DTO (Q001TranrsItems)
        List<Q001TranrsItems> items = projectionPage.getContent().stream()
                .map(proj -> {
                    // 使用 ObjectMapper 進行轉換
                    Q001TranrsItems item = om.convertValue(proj, Q001TranrsItems.class);
                    // 格式化日期時間
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateString = proj.getUpdateTime() != null ? proj.getUpdateTime().format(formatter) : "";
                    item.setUpdateTime(dateString);
                    return item;
                }).collect(Collectors.toList());

        // 準備成功的回應訊息
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORQ001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");

        // 組合分頁的回應資料
        Q001Tranrs createTranrs = new Q001Tranrs();
        createTranrs.setPageSize(projectionPage.getSize());
        createTranrs.setPageNumber(projectionPage.getNumber());
        createTranrs.setTotalPage(projectionPage.getTotalPages());
        createTranrs.setTotalCount(projectionPage.getTotalElements());
        createTranrs.setItems(items);

        // 組合完整的回應物件
        StoreResponse<Q001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * 根據店家 ID 查詢特定店家資料
     * 
     * @param storeRequest 包含店家 ID 的請求
     * @return 包含店家資料的回應
     * @throws ErrorInputException   如果輸入資料有誤
     * @throws DataNotFoundException 如果查無資料
     */
    @Override
    public StoreResponse<Q001Tranrs> findByStoreId(StoreRequest<Q001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {

        // 從請求中獲取店家 ID
        Q001Tranrq data = storeRequest.getTranrq();
        Integer storeId = data.getStoreId();

        // 使用
        // repo 查詢店家，如果找不到就拋出例外
        StoreEntity storeEntity = storeRepo.findByStoreId(storeId)
                .orElseThrow(() -> new DataNotFoundException());

        // 將查詢到的實體轉換成回傳用的 DTO
        Q001TranrsItems itemDto = om.convertValue(storeEntity, Q001TranrsItems.class);

        // 將 DTO 加入到 List 中
        List<Q001TranrsItems> items = new ArrayList<>();
        items.add(itemDto);

        // 準備成功的回應訊息
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORQ001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");

        // 組合回應資料 (這裡因為是單筆查詢，所以分頁資訊都設為 0 或 null)
        Q001Tranrs createTranrs = new Q001Tranrs();
        createTranrs.setPageSize(0);
        createTranrs.setPageNumber(0);
        createTranrs.setTotalPage(0);
        createTranrs.setTotalCount(null);
        createTranrs.setItems(items);

        // 組合完整的回應物件
        StoreResponse<Q001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * 更新店家資料
     * 
     * @param storeRequest 包含要更新的店家資料的請求
     * @return 包含操作結果的回應
     * @throws ErrorInputException   如果輸入資料有誤
     * @throws DataNotFoundException 如果找不到要更新的店家
     */
    @Override
    public StoreResponse<T001Tranrs> updateStore(StoreRequest<T001Tranrq> storeRequest)
            throws ErrorInputException, DataNotFoundException {

        // 從請求中獲取要更新的資料
        T001Tranrq data = storeRequest.getTranrq();
        // 先從資料庫撈出舊資料，如果撈不到就拋出例外
        StoreEntity storeEntity = storeRepo.findByStoreId(data.getStoreId())
                .orElseThrow(() -> new DataNotFoundException());

        // 將傳入的新資料更新到從資料庫撈出來的實體物件上
        storeEntity.setStoreName(data.getStoreName());
        storeEntity.setOwner(data.getOwner());
        storeEntity.setTel(data.getTel());
        storeEntity.setFax(data.getFax());
        storeEntity.setMobile(data.getMobile());
        storeEntity.setAddress(data.getAddress());
        storeEntity.setEvaluation(data.getEvaluation());
        storeEntity.setRemarks(data.getRemarks());
        // JPA Auditing 會自動更新 updateTime，所以這裡手動設定會被覆蓋
        storeEntity.setUpdateTime(LocalDateTime.now());
        // 儲存更新後的實體
        storeRepo.save(storeEntity);

        // 準備成功的回應訊息
        T001Tranrs createTranrs = new T001Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORET001");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");

        // 組合完整的回應物件
        StoreResponse<T001Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }

    /**
     * 刪除店家資料
     * 
     * @param storeRequest 包含要刪除的店家 ID 的請求
     * @return 包含操作結果的回應
     * @throws DataNotFoundException 如果找不到要刪除的店家
     */
    @Override
    public StoreResponse<T003Tranrs> deleteStore(StoreRequest<T003Tranrq> storeRequest)
            throws DataNotFoundException {
        T003Tranrq data = storeRequest.getTranrq();
        // 呼叫 repo 的刪除方法，如果刪除不成功 (找不到資料)，就拋出例外
        storeRepo.deleteByStoreId(data.getStoreId())
                .orElseThrow(() -> new DataNotFoundException());

        // 準備成功的回應訊息
        T003Tranrs createTranrs = new T003Tranrs();
        MwHeader createMwheader = new MwHeader();
        createMwheader.setMsgid("XXA-C-STORET003");
        createMwheader.setReturncode("0000");
        createMwheader.setReturndesc("交易成功");

        // 組合完整的回應物件
        StoreResponse<T003Tranrs> res = new StoreResponse<>();
        res.setMwheader(createMwheader);
        res.setTranrs(createTranrs);
        return res;
    }
}
