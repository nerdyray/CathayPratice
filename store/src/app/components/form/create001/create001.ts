// 匯入 Angular 核心模組
import { CommonModule } from '@angular/common'; // 提供 *ngIf, *ngFor 等基本指令
import { ChangeDetectorRef, Component, EventEmitter, OnInit, Output } from '@angular/core'; // 元件的核心功能
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms'; // 處理表單的功能
import { Router } from '@angular/router'; // 處理頁面跳轉

// 匯入自訂的資料介面 (Interface) 與服務 (Service)
import { Q001Tranrq } from './../../../interface/Q001Tranrq';   // 查詢店家 Request
import { Store } from '../../../interface/store';                 // 店家資料物件
import { StoreStateService } from '../../store-list-state/storeListState'; // 跨元件狀態管理服務
import { StoreService } from './../../../services/storeService';   // API 服務

/**
 * @Component: Angular 的裝飾器，用來定義一個「元件」
 * 包含 HTML 樣板、CSS 樣式和 TypeScript 程式邏輯
 */
@Component({
  selector: 'app-create001', // 在 HTML 中使用 <app-create001> 來嵌入此元件
  imports: [CommonModule, ReactiveFormsModule], // 匯入此獨立元件需要的模組
  templateUrl: './create001.html', // HTML 樣板檔案
  styleUrl: './create001.css',     // CSS 樣式檔案
})
export class Create001 implements OnInit {

  // 表單欄位驗證用的正規表示式
  PATTERN_TEL: RegExp = /^[0-9]{1,15}$/; // 驗證電話，允許 1-15 位數字
  PATTERN_STRING_WITH_NUM: RegExp = /^[\u4e00-\u9fa5a-zA-Z0-9\s\-\.\,]+$/; // 驗證字串，允許中文、英文、數字和部分符號

  // 元件內部狀態變數
  storeForm!: FormGroup; // 用來管理整個表單的物件
  showErrorToast = false;   // 控制「錯誤」提示訊息的顯示
  showSuccessToast = false; // 控制「成功」提示訊息的顯示
  submited = false;         // 標記表單是否已送出

  /**
   * 建構函式 (Constructor)
   * Angular 會自動「注入」我們需要的服務，方便我們在元件中使用
   */
  constructor(
    private fb: FormBuilder,                 // 表單建立工具
    private storeService: StoreService,      // API 呼叫服務
    private cdr: ChangeDetectorRef,          // 手動觸發變更偵測的工具
    private router: Router,                  // 路由服務，用來跳轉頁面
    private storeState: StoreStateService    // 跨元件狀態管理服務
  ) { }

  /**
   * ngOnInit: 元件的生命週期掛鉤 (Lifecycle Hook)
   * 會在元件初始化時執行一次，適合放初始設定
   */
  ngOnInit(): void {
    // 使用 FormBuilder 建立表單 (storeForm)
    this.storeForm = this.fb.group({
      // 定義表單中的欄位與其「驗證規則」
      // 'storeName' 欄位: 必填, 長度 1-10, 且需符合特定格式
      storeName: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(10), Validators.pattern(this.PATTERN_STRING_WITH_NUM)]],
      // 'owner' 欄位: 必填, 需為中英文
      owner: ['', [Validators.required, Validators.pattern(/^[\u4e00-\u9fa5a-zA-Z]+$/)]],
      // 'tel' 欄位: 必填, 需符合電話格式
      tel: ['', [Validators.required, Validators.pattern(this.PATTERN_TEL)]],
      // 'evaluation' 欄位: 必填
      evaluation: ['', [Validators.required]],
      // 'fax' 欄位: 選填, 但若填寫需符合電話格式
      fax: ['', [Validators.pattern(this.PATTERN_TEL)]],
      // 'mobile' 欄位: 選填, 但若填寫需符合電話格式
      mobile: ['', [Validators.pattern(this.PATTERN_TEL)]],
      // 'address' 欄位: 選填, 長度最多 100
      address: ['', [Validators.maxLength(100)]],
      // 'updateDate' 欄位: 給定預設值且不可編輯
      updateDate: [{ value: '2022-05-16', disabled: true }]
    });
  }

  /**
   * 點擊「新增」按鈕時觸發
   */
  onAdd(): void {
    // 步驟 1: 將所有欄位標記為已碰觸，這樣才會顯示錯誤訊息
    this.storeForm.markAllAsTouched();

    // 步驟 2: 檢查表單驗證是否通過
    if (this.storeForm.invalid) {
      this.showErrorToast = true; // 顯示錯誤提示
      return; // 中斷執行
    }

    // 步驟 3: 組合要送到後端的資料
    const rawData = this.storeForm.getRawValue(); // 取得表單所有欄位的值
    const store: Store = { ...rawData };         // 將表單值轉為 Store 物件

    // 步驟 4: 呼叫 API 服務來新增資料
    this.storeService.addStore(store).subscribe({
      // `subscribe` 用來接收 API 的回應
      // `next` 表示 API 成功回傳資料
      next: (res) => {
        this.showSuccessToast = true; // 顯示成功提示
        this.submited = true;
        this.showErrorToast = false;
        this.cdr.detectChanges();     // 手動觸發畫面更新

        // 建立新的查詢條件，讓列表頁返回時能直接搜尋到這筆新資料
        const newCondition: Q001Tranrq = {
          storeName: store.storeName,
          page: { pageNumber: 0, pageSize: 10 }
        };
        // 透過狀態管理服務，通知其他元件 (如列表頁) 新增成功
        this.storeState.setCreatedSuccessState(newCondition);
      },
      // `error` 表示 API 發生錯誤
      error: (err) => {
        console.error('新增失敗:', err); // 在開發者工具中印出錯誤，方便除錯
        this.showErrorToast = true;      // 顯示錯誤提示給使用者
      }
    });
  }

  /**
   * 點擊「清除」按鈕時觸發
   */
  onClear(): void {
    // 重設表單所有欄位的值
    this.storeForm.reset({
      storeName: '',
      owner: '',
      tel: '',
      fax: '',
      mobile: '',
      address: '',
      evaluation: '',
      updateDate: '2022-05-16' // 將日期恢復預設值
    });
    this.submited = false;
  }

  /**
   * 點擊「返回」按鈕時觸發
   */
  onBack(): void {
    // 使用 router 跳轉回店家列表頁
    this.router.navigate(['/store/list']);
  }

  /**
   * 關閉提示訊息
   */
  closeToast(): void {
    this.showErrorToast = false;
    this.showSuccessToast = false;
  }
}
