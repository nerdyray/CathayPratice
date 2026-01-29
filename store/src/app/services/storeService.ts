import { CreateStoreTranrq } from './../interface/createTranrq';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Store } from '../interface/store';
import { Q001Tranrq } from '../interface/Q001Tranrq';
import { T001Tranrq } from '../interface/T001Tranrq';



@Injectable({
  providedIn: 'root',
})
export class StoreService {
  constructor(private http: HttpClient) { }

  private createUrl = 'http://localhost:8080/store/create';
  private queryUrl = 'http://localhost:8080/store/query';
  private updateUrl = 'http://localhost:8080/store/maintain'
  private queryStoreUrl = 'http://localhost:8080/store/querystore';



  addStore(store: Store): Observable<any> {
    const createTranrq: CreateStoreTranrq = {
      MWHEADER: {
        MSGID: ' XXA-C-STORET002'
      },
      TRANRQ: {
        storeName: store.storeName,
        owner: store.owner,
        tel: store.tel,
        fax: store.fax,
        mobile: store.mobile,
        address: store.address,
        remarks: store.remarks,
        evaluation: '',
        date: store.updateTime || new Date().toLocaleDateString()
      }
    };
    return this.http.post(this.createUrl, createTranrq);
  }

  findAllStore(requestBody: Q001Tranrq): Observable<any> {
    const queryTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STOREQ001'
      },
      // [重點] 這裡直接把組好的巢狀物件放進去
      TRANRQ: requestBody
    };
    return this.http.post(this.queryUrl, queryTranrq);
  }

  updateStore(requestBody: T001Tranrq): Observable<any> {
    const updateTranrq = {
      MWHEADER: {
        MSGID: 'XXA-C-STORET001'
      },
      TRANRQ: requestBody
    };
    return this.http.post(this.updateUrl, updateTranrq);
  }
  findByStoreId(payload: any): Observable<any> {
    // 對應後端的 @PostMapping("/querystore")
    return this.http.post(`${this.queryStoreUrl}`, payload);
  }
}
