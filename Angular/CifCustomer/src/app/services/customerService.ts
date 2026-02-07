
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Q003Tranrq } from '../interface/Q003Tranrq';

import { Data } from '@angular/router';
import { Q002Tranrq } from '../interface/Q002Tranrq';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  deleteCustomer(id: string) {
    throw new Error('Method not implemented.');
  }
  private checkUrl = 'http://localhost:8080/cif/checkId';
  private createUrl = 'http://localhost:8080/cif/create';       // 「新增」店家的 API
  private listUrl = 'http://localhost:8080/cif/filter';
  constructor(private http: HttpClient) { }

  checkId(requestBody: Q003Tranrq): Observable<any> {
    const checkIdTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STOREQ003' // 查詢交易的代號
      },
      TRANRQ: requestBody // 將傳入的查詢條件直接放入請求主體
    };
    return this.http.post(this.checkUrl, checkIdTranrq);
  }

  addCustomer(requestBody: Data): Observable<any> {
    const createTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET001'
      },
      TRANRQ: {
        DATA: requestBody
      }
    };
    return this.http.post(this.createUrl, createTranrq);
  }
  //搜尋所有客戶（一頁五筆資料）
listCustomer(pageNum: number, pageSize: number): Observable<any> {    console.log('搜尋所有資料');
    const requestBody = {
      MWHEADER: {
        MSGID: "XXA-C-CIFQ002"
      },
      TRANRQ: {
        PAGE: {
          pageNumber: pageNum, // 直接使用傳進來的頁碼
          pageSize: pageSize
        },
        // 雖然沒有搜尋條件，但必須符合規格書結構
        DATA: {
          idNum: "",
          chineseName: "",
          gender: "",
          education: "",
          mobile: "",
          email: "",
          year: 0
        },
        SORTINFO: {
          sortBy: "DESC",
          sortColumn: "ORDER_ID"
        }
      }
    };
    return this.http.post(this.listUrl, requestBody);
  }
}
