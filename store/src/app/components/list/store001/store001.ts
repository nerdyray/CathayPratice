import { Component, OnChanges, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // 需要用 ngModel
import { Store } from '../../../interface/store';
import { StoreService } from '../../../services/storeService';
import { Q001Tranrq } from '../../../interface/Q001Tranrq';
import { ApiResponse } from '../../../interface/StoreItem';

@Component({
  selector: 'app-store001',
  imports: [CommonModule, FormsModule],
  templateUrl: './store001.html',
  styleUrl: './store001.css',
})
export class Store001 implements OnInit {
  // 資料列表
  storeList: Store[] = [];

  // 搜尋條件
  searchKeyword: string = '';

  currentPage: number = 1;  // 當前頁碼 (前端顯示通常從 1 開始)
  pageSize: number = 10;    // 每頁顯示幾筆
  totalItems: number = 0;
  // 目前選中的那筆資料
  selectedStore: Store | null = null;

  constructor(private storeService: StoreService) { }

  ngOnInit(): void {
    // 進頁面先查詢一次所有資料
    this.onSearch();
  }


  // 查詢功能
  onSearch(pageIdx: number = 1): void {
    this.currentPage = pageIdx;
    const queryCondition: Q001Tranrq = {
      storeName: this.searchKeyword || '',
      page: {
        pageNumber: this.currentPage - 1,
        pageSize: this.pageSize
      }
    };
    this.storeService.findAllStore(queryCondition).subscribe({
      next: (res) => {
        console.log("沒有資料", res);
        if (res && res.TRANRS) {
          this.storeList = res.TRANRS.items || []; // 資料清單
          this.totalItems = res.TRANRS.totalElements; // 總筆數 (給分頁算頁數用)
        } else {
          this.storeList = [];
          this.totalItems = 0;
        }
      },
      error: (err) => console.error('連線失敗' + err)
    });
  }

  // 清除功能
  onClear(): void {
    this.searchKeyword = '';
    this.onSearch();
  }

  // 選取某一列
  onSelect(store: Store): void {
    this.selectedStore = store;
  }

  // 導向新增頁面 (這裡看你是要路由跳轉，還是切換元件)
  onNavigateToAdd(): void {
    console.log('跳轉到新增頁面...');

  }

  // 修改功能
  onEdit(): void {
    if (!this.selectedStore) return;
    console.log('準備修改:', this.selectedStore);
    // TODO: 實作修改邏輯，例如帶參數跳轉
  }

  // 刪除功能
  onDelete(): void {
    if (!this.selectedStore) return;
    const confirmDelete = confirm(`確定要刪除 ${this.selectedStore.storeName} 嗎？`);

    if (confirmDelete) {
      console.log('執行刪除...');
    }
  }
}
