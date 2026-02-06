
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Q003Tranrq } from '../interface/Q003Tranrq';

import { Data } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  deleteCustomer(id: string) {
    throw new Error('Method not implemented.');
  }
  private checkUrl = 'http://localhost:8080/cif/checkId';
  private createUrl = 'http://localhost:8080/cif/create';       // 「新增」店家的 API

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
}
