import { Injectable, signal } from '@angular/core';
import { Q001Tranrq } from '../../interface/Q001Tranrq';

// 定義狀態物件的資料結構
export interface StoreListState {
  searchCriteria: Q001Tranrq | null; // 上一次的搜尋條件
  searchResults: any[];              // 上一次的搜尋結果 (作為快取)
  targetMode: 'CACHE' | 'RELOAD_NEW'; // 返回列表頁時的模式
}

/**
 * 這是一個可注入的服務 (Service)，在整個應用程式中只會有一個實例。
 * 主要用途是在「查詢頁」、「新增頁」、「編輯頁」之間共享狀態，
 * 以達到返回查詢頁時能快取資料，或是能自動搜尋剛新增/修改的資料。
 */
@Injectable({
  providedIn: 'root'
})
export class StoreStateService {

  // 定義初始狀態
  private initialState: StoreListState = {
    searchCriteria: null,
    searchResults: [],
    targetMode: 'CACHE' // 預設為快取模式
  };

  // 使用 Angular Signal 來管理狀態，當狀態改變時，相關的元件會自動更新
  storeState = signal<StoreListState>(this.initialState);

  constructor() { }

  /**
   * 當在查詢頁面「搜尋完成」後呼叫此方法。
   * @param criteria - 當前的搜尋條件
   * @param results - 當前的搜尋結果
   */
  saveSearchState(criteria: Q001Tranrq, results: any[]) {
    this.storeState.set({
      searchCriteria: criteria,
      searchResults: results,
      targetMode: 'CACHE' // 保存完畢後，模式設為快取
    });
  }

  /**
   * 當在新增頁面「新增成功」後呼叫此方法。
   * @param newStoreParams - 包含新店家資訊的搜尋條件
   */
  setCreatedSuccessState(newStoreParams: Q001Tranrq) {
    this.storeState.set({
      searchCriteria: newStoreParams, // 設定下次要搜尋的條件
      searchResults: [],              // 清空舊的搜尋結果
      targetMode: 'RELOAD_NEW'        // 將模式設為「重新載入」，強制查詢頁刷新
    });
  }

  /**
   * 讓元件可以取得目前的狀態 (此為唯讀，不可修改)
   */
  getState() {
    return this.storeState();
  }

  /**
   * 清除所有狀態，還原到初始值。
   * (例如：使用者登出時)
   */
  clearState() {
    this.storeState.set(this.initialState);
  }
}
