// 匯入 Angular 核心模組
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // 匯入 FormsModule 以使用 ngModel
import { Router } from '@angular/router';

// 匯入自訂的資料介面與服務
import { Store } from '../../../interface/store';
import { StoreService } from '../../../services/storeService';
import { Q001Tranrq } from '../../../interface/Q001Tranrq';
import { StoreStateService } from '../../store-list-state/storeListState';

@Component({
  selector: 'app-store001',
  imports: [CommonModule, FormsModule], // 匯入此獨立元件需要的模組
  templateUrl: './store001.html',
  styleUrl: './store001.css',
})
export class Store001 implements OnInit {

  storeList: Store[] = []; // 存放從 API 取得的店家列表
  searchKeyword: string = ''; // 綁定搜尋輸入框的文字

  // 分頁相關
  currentPage: number = 1;
  pageSize: number = 10;
  totalItems: number = 0;

  // 用來儲存使用者在表格中選取的店家資料
  selectedStore: Store | null = null;

  constructor(
    private storeService: StoreService, // API 服務
    private router: Router,             // 路由服務
    private cdr: ChangeDetectorRef,     // 變更偵測服務
    private storeState: StoreStateService // 狀態管理服務
  ) { }

  ngOnInit(): void {
    // 元件初始化時，檢查是否有來自其他頁面的狀態
    const state = this.storeState.getState();

    if (state.targetMode === 'RELOAD_NEW' && state.searchCriteria) {
      // 情況 A: 從「新增成功」頁面回來，要用新資料的條件重新搜尋
      this.searchKeyword = state.searchCriteria.storeName || '';
      this.onSearch(1);

    } else if (state.targetMode === 'CACHE' && state.searchResults.length > 0) {
      // 情況 B: 從「編輯/返回」頁面回來，直接讀取快取，不用重打 API
      this.storeList = state.searchResults;
      if (state.searchCriteria) {
        this.searchKeyword = state.searchCriteria.storeName || '';
      }
      this.cdr.detectChanges(); // 手動觸發畫面更新
    }
  }

  // 點擊「查詢」按鈕
  onSearch(pageIdx: number = 1): void {
    this.currentPage = pageIdx;
    const queryCondition: Q001Tranrq = {
      storeName: this.searchKeyword || '',
      page: {
        pageNumber: this.currentPage - 1, // 後端分頁通常從 0 開始
        pageSize: this.pageSize
      }
    };
    this.storeService.findAllStore(queryCondition).subscribe({
      next: (res) => {
        if (res && res.TRANRS) {
          this.storeList = [...res.TRANRS.items]; // 更新店家列表
          this.totalItems = res.TRANRS.totalElements; // 更新總筆數
          // 將這次的搜尋條件與結果存到狀態服務中，供下次返回使用
          this.storeState.saveSearchState(queryCondition, this.storeList);
          this.cdr.detectChanges();
        } else {
          this.storeList = [];
          this.totalItems = 0;
          this.storeState.saveSearchState(queryCondition, []);
        }
      },
      error: (err) => console.error('查詢失敗', err)
    });
  }

  // 點擊「清除」按鈕
  onClear(): void {
    this.searchKeyword = '';
    this.onSearch(); // 清空後重新查詢所有資料
  }

  // 當使用者點擊表格中的某一列
  onSelect(store: Store): void {
    this.selectedStore = store; // 將點選的店家資料存起來
  }

  // 點擊「新增」按鈕，跳轉到新增頁面
  onNavigateToAdd(): void {
    this.router.navigate(['/store/create']);
  }

  // 點擊「修改」按鈕
  onEdit(): void {
    if (!this.selectedStore) {
      alert('請先選擇一筆要修改的資料！');
      return;
    }
    const targetId = this.selectedStore.storeId;
    // 跳轉到修改頁面，並將店家的 ID 帶在 URL 上
    this.router.navigate(['/store/update', targetId]);
  }

  // 點擊「刪除」按鈕
  onDelete(): void {
    if (!this.selectedStore) return;

    // 跳出確認對話框，防止誤刪
    const confirmDelete = confirm(`確定要刪除 ${this.selectedStore.storeName} 嗎？`);
    if (confirmDelete) {
      const deleteReq = { storeId: this.selectedStore.storeId };
      this.storeService.deleteStore(deleteReq as any).subscribe({
        next: (res) => {
          alert('刪除成功');
          this.onSearch(); // 刪除後重新整理列表
          this.selectedStore = null; // 清除選取狀態
        },
        error: (err) => {
          console.error('刪除失敗', err);
          alert('刪除失敗：' + (err.message || '未知錯誤'));
        }
      });
    };
  }
}
