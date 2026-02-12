// 導入 HttpClient 模組，用於發送 HTTP 請求
import { HttpClient } from '@angular/common/http';
// 導入 Injectable 裝飾器，標記這個類為一個可被注入的服務
import { Injectable } from '@angular/core';
// 導入 Observable 類型，用於處理異步操作的數據流
import { Observable } from 'rxjs';
// 導入 Q003Tranrq 接口，定義身份證驗證請求的數據結構
import { Q003Tranrq } from '../interface/Q003Tranrq';

// 導入 Q002Tranrq 接口中的 Data，定義客戶查詢的數據結構 (注意：此處應該是從 Q002Tranrq 導入 Data)
import { Data as Q002Data } from '../interface/Q002Tranrq';
// 導入 T001Tranrq 接口中的 Data，定義客戶新增的數據結構
import { Data as T001Data } from '../interface/T001Tranrq';
import { Data as T002Data } from '../interface/T002Tranrq';


/**
 * CustomerService 是一個可注入的服務，用於處理與客戶相關的後端 API 請求。
 * `providedIn: 'root'` 表示這個服務在應用程式的根級別提供，所有組件都可以使用它。
 */
@Injectable({
  providedIn: 'root',
})
export class CustomerService {


  // API 端點定義
  private checkUrl = 'http://localhost:8080/cif/checkId'; // 檢查身份證字號是否重複的 API
  private createUrl = 'http://localhost:8080/cif/create';       // 新增客戶的 API
  private listUrl = 'http://localhost:8080/cif/filter';         // 查詢客戶列表的 API (過濾)
  private deleteUrl = 'http://localhost:8080/cif/deleteInfo';         // 刪除客戶的 API
  private editUrl = 'http://localhost:8080/cif/editInfo';       // 編輯客戶的 API

  /**
   * 構造函數，注入 HttpClient 服務。
   * @param http HttpClient 實例，用於發送 HTTP 請求。
   */
  constructor(private http: HttpClient) { }

  /**
   * 檢查身份證字號是否已存在。
   * @param requestBody 包含身份證字號的請求數據。
   * @returns 包含後端響應的 Observable。
   */
  checkId(requestBody: Q003Tranrq): Observable<any> {
    // 構建請求主體，包含消息頭和交易請求數據
    const checkIdTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STOREQ003' // 查詢交易的代號
      },
      TRANRQ: requestBody // 將傳入的查詢條件直接放入請求主體
    };
    // 發送 POST 請求到 checkId API
    return this.http.post(this.checkUrl, checkIdTranrq);
  }

  /**
   * 新增客戶資料。
   * @param requestBody 包含客戶資料的請求數據。
   * @returns 包含後端響應的 Observable。
   */
  addCustomer(requestBody: T001Data): Observable<any> {
    // 構建請求主體，包含消息頭和交易請求數據
    const createTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET001'
      },
      TRANRQ: {
        DATA: requestBody // 將傳入的客戶數據放入請求主體
      }
    };
    // 發送 POST 請求到 create API
    return this.http.post(this.createUrl, createTranrq);
  }

  /**
   * 搜尋所有客戶列表，支持分頁和查詢條件。
   * @param pageNum 頁碼 (從 1 開始)。
   * @param pageSize 每頁顯示的筆數。
   * @param searchParams 查詢參數。
   * @returns 包含後端響應的 Observable。
   */
  listCustomer(pageNum: number, pageSize: number, searchParams: any): Observable<any> {
    console.log('搜尋所有資料');
    // 構建請求主體，包含消息頭、分頁資訊、數據過濾條件和排序資訊
    const requestBody = {
      // 訊息標頭，用於識別交易類型
      MWHEADER: {
        MSGID: "XXA-C-CIFQ002"
      },
      // 交易請求主體
      TRANRQ: {
        // 分頁相關資訊
        PAGE: {
          pageNumber: pageNum, // 當前頁碼
          pageSize: pageSize   // 每頁筆數
        },
        // 查詢條件數據
        DATA: {
          idNum: searchParams.idNum || "",
          chineseName: searchParams.chineseName || "",
          gender: searchParams.gender || "",
          education: searchParams.education || "",
          mobile: searchParams.mobile || "",
          email: searchParams.email || "",
          year: searchParams.year || 0 // 注意這裡將 year 映射到 year
        } as Q002Data, // 類型斷言為 Q002Data 以符合結構
        // 排序相關資訊
        SORTINFO: {
          sortBy: "DESC",       // 排序方向：降序
          sortColumn: "ORDER_ID"// 排序欄位：訂單ID
        }
      }
    };
    // 發送 POST 請求到 list API
    return this.http.post(this.listUrl, requestBody);
  }
  /**
   * 刪除客戶
   * @param orderId 訂單 ID
   */
  deleteCustomer(orderId: number): Observable<any> {
    // 組裝 Request Body
    const requestBody = {
      MWHEADER: {
        MSGID: " XXA-C-CIFT003"
      },
      TRANRQ: {
        order_id: orderId
      }
    };

    // 發送 POST 請求 (通常 API 雖然是刪除動作，但若是透過 Transaction Request Body 傳遞，常使用 POST)
    return this.http.post(this.deleteUrl, requestBody);
  }

  /**
   * 編輯客戶資料。
   * @param requestBody 包含要更新的客戶資料。
   * @returns 包含後端響應的 Observable。
   */
  editCustomer(requestBody: T002Data): Observable<any> {
    // 構建請求主體，包含消息頭和交易請求數據
    const editTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET002'
      },
      TRANRQ: {
        DATA: requestBody // 將傳入的客戶數據放入請求主體
      }
    };
    // 發送 POST 請求到 edit API
    return this.http.post(this.editUrl, editTranrq);
  }
}

