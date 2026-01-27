import { Component, OnChanges, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // 需要用 ngModel
import { Store } from '../../../interface/store';
import { StoreService } from '../../../services/storeService';
import { Q001Tranrq } from '../../../interface/Q001Tranrq';

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
      // 1. 搜尋條件
      storeName: this.searchKeyword || '', // 避免傳 null，若為空傳空字串 (視後端驗證而定)
      // storeId: ... (如果有要搜 ID 再填)

      // 2. 分頁物件 (巢狀)
      page: {
        // 注意：Java 的 PageRequest 通常從 0 開始，但前端顯示從 1 開始
        // 所以這裡通常要減 1。請確認你的後端邏輯。
        pageNumber: this.currentPage - 1,
        pageSize: this.pageSize
      }
    };

    console.log('準備送出的 Payload:', queryCondition);

    // 3. 呼叫 Service
    this.storeService.findAllStore(queryCondition).subscribe({
      next: (res) => {
        // ... 處理回傳資料
      },
      error: (err) => console.error(err)
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
