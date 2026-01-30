// 引入 Angular 和 RxJS 的核心功能
import { Injectable } from '@angular/core';      // 引入 Injectable 裝飾器，讓這個類別可以被「注入」到其他地方使用
import { HttpClient } from '@angular/common/http'; // 引入 HttpClient，這是 Angular 用來發送網路請求 (例如：GET, POST) 的工具
import { Observable } from 'rxjs';                 // 引入 Observable，這是一種處理非同步資料的方式，常用於網路請求

// 引入我們自己定義的資料格式，讓前後端溝通有統一的標準
import { CreateStoreTranrq } from './../interface/createTranrq'; // 引入「建立店家」時，需要傳送給後端的資料格式
import { Store } from '../interface/store';                     // 引入「店家」這個物件的資料格式
import { Q001Tranrq } from '../interface/Q001Tranrq';             // 引入「查詢店家」時，需要傳送給後端的資料格式
import { EditTranrq } from '../interface/T001Tranrq';             // 引入「更新店家」時，需要傳送給後端的資料格式
import { T003Tranrq } from '../interface/T003Tranrq';             // 引入「刪除店家」時，需要傳送給後端的資料格式

/**
 * @Injectable: 這是一個 Angular 裝飾器，告訴 Angular 這個 `StoreService` 是一個「服務 (Service)」。
 * providedIn: 'root' 的意思是，這個服務在整個應用程式中只會有一個實例（單例模式），
 * 你可以在任何需要的元件或服務中，直接注入並使用它，而不用自己手動建立。
 */
@Injectable({
  providedIn: 'root',
})
export class StoreService {

  // --- 後端 API 的網址 ---
  // 在正式專案中，建議將這些網址統一放在 environment.ts 檔案中管理，
  // 這樣可以方便地區分開發環境和正式環境的 API 位置。
  private createUrl = 'http://localhost:8080/store/create';       // 「新增」店家的 API
  private queryUrl = 'http://localhost:8080/store/query';        // 「查詢」所有店家的 API
  private updateUrl = 'http://localhost:8080/store/maintain';    // 「更新」店家資訊的 API
  private queryStoreUrl = 'http://localhost:8080/store/querystore'; // 根據 ID 「查詢」單一店家的 API
  private deleteUrl = 'http://localhost:8080/store/delete';      // 「刪除」店家的 API

  /**
   * StoreService 的建構函式
   * Angular 的「依賴注入 (Dependency Injection)」機制會自動將 HttpClient 的實例傳入這裡，
   * 讓我們的 Service 可以使用 HttpClient 來發送請求。
   * @param http - Angular 的 HTTP 客戶端，用於與後端 API 溝通
   */
  constructor(private http: HttpClient) { }

  /**
   * 新增一筆店家資料
   * @param store - 從使用者介面 (UI) 傳入的店家物件
   * @returns 回傳一個 Observable。呼叫這個函式的地方需要「訂閱 (subscribe)」它，才會真正地發送網路請求。
   */
  addStore(store: Store): Observable<any> {
    // 根據後端 API 的規格，將傳入的 store 物件組合成規定的請求格式
    const createTranrq: CreateStoreTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET002' // 交易代號，用來讓後端識別這次請求的目的
      },
      TRANRQ: {
        storeName: store.storeName,
        owner: store.owner,
        tel: store.tel,
        fax: store.fax,
        mobile: store.mobile,
        address: store.address,
        remarks: store.remarks,
        evaluation: store.evaluation,
        // 如果店家物件中沒有提供 updateTime，就使用當前的日期
        date: store.updateTime || new Date().toLocaleDateString()
      }
    };
    // 使用 HttpClient 的 post 方法，將組合好的請求資料 (createTranrq) 發送到指定的 API 網址 (createUrl)
    return this.http.post<any>(this.createUrl, createTranrq);
  }

  /**
   * 根據條件查詢店家列表（支援分頁和篩選功能）
   * @param requestBody - 包含查詢條件（例如：店家名稱）和分頁資訊的物件
   * @returns 回傳一個 Observable，其中會包含後端回傳的店家列表和分頁結果
   */
  findAllStore(requestBody: Q001Tranrq): Observable<any> {
    // 組合查詢請求的資料格式
    const queryTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STOREQ001' // 查詢交易的代號
      },
      TRANRQ: requestBody // 將傳入的查詢條件直接放入請求主體
    };
    // 發送 POST 請求到後端的查詢 API
    return this.http.post(this.queryUrl, queryTranrq);
  }

  /**
   * 更新一筆現有的店家資料
   * @param requestBody - 包含要更新的店家 ID 和新的資料
   * @returns 回傳一個 Observable
   */
  updateStore(requestBody: EditTranrq): Observable<any> {
    // 組合更新請求的資料格式
    const updateTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET001' // 更新交易的代號
      },
      TRANRQ: requestBody
    };
    // 發送 POST 請求到後端的更新 API
    return this.http.post(this.updateUrl, updateTranrq);
  }

  /**
   * 根據店家 ID 查詢單一一筆店家的詳細資料
   * @param payload - 通常是一個包含 storeId 的物件，例如 { storeId: 'S001' }
   * @returns 回傳一個 Observable，其中會包含該店家的詳細資料
   */
  findByStoreId(payload: any): Observable<any> {
    // 這個 API 的請求格式比較特別，直接將傳入的 payload 當作請求的主體，
    // 沒有外層的 MWHEADER 和 TRANRQ。
    return this.http.post(`${this.queryStoreUrl}`, payload);
  }

  /**
   * 根據店家 ID 刪除一筆資料
   * @param requestBody - 包含要刪除的店家 ID
   * @returns 回傳一個 Observable
   */
  deleteStore(requestBody: T003Tranrq): Observable<any> {
    // 組合刪除請求的資料格式
    const deleteTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET003' // 刪除交易的代號
      },
      TRANRQ: requestBody
    }
    // 發送 POST 請求到後端的刪除 API
    return this.http.post(`${this.deleteUrl}`, deleteTranrq)
  }
}
