// 導入 Angular 核心模組中的 Component 裝飾器和 OnInit 生命週期鉤子
import { Component, OnInit } from '@angular/core';
// 導入 CommonModule，提供常用的 Angular 指令如 ngIf, ngFor
import { CommonModule } from '@angular/common';
// 導入 Angular 表單模組中的 ReactiveFormsModule (響應式表單)、FormsModule (模板驅動表單)、FormBuilder (表單構建器) 和 FormGroup (表單組)
import { ReactiveFormsModule, FormsModule, FormBuilder, FormGroup } from '@angular/forms';

// 導入 Angular Material 相關模組
import { MatTableDataSource, MatTableModule } from '@angular/material/table'; // 用於表格數據源和表格組件
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator'; // 用於分頁器和分頁事件
import { MatSidenavModule } from '@angular/material/sidenav'; // 側邊導航 (雖然此組件未使用，但保留在 MATERIAL_MODULES 中)
import { MatListModule } from '@angular/material/list';     // 列表 (雖然此組件未使用，但保留在 MATERIAL_MODULES 中)
import { MatIconModule } from '@angular/material/icon';     // 圖標
import { MatFormFieldModule } from '@angular/material/form-field'; // 表單欄位
import { MatInputModule } from '@angular/material/input';       // 輸入框
import { MatSelectModule } from '@angular/material/select';     // 選擇框
import { MatRadioModule } from '@angular/material/radio';       // 單選按鈕
import { MatCheckboxModule } from '@angular/material/checkbox'; // 複選框
import { MatButtonModule } from '@angular/material/button';     // 按鈕
import { MatSliderModule } from '@angular/material/slider';     // 滑塊
import { MatDialog } from '@angular/material/dialog';           // 對話框
import { MatSnackBar } from '@angular/material/snack-bar';     // 消息提示

// 導入自定義的服務和接口
import { Data } from '../../../interface/Q002Tranrq'; // 用於查詢請求的數據接口
import { CustomerService } from '../../../services/customerService'; // 客戶服務，用於與後端 API 交互

// 定義一個常數陣列，包含所有會用到的 Angular Material 模組，方便在 @Component 的 imports 中引用
const MATERIAL_MODULES = [
  MatSidenavModule,
  MatListModule,
  MatIconModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatRadioModule,
  MatCheckboxModule,
  MatButtonModule,
  MatSliderModule,
  MatTableModule,
  MatPaginatorModule
];

/**
 * Cif001 組件，用於顯示 CIF 客戶列表，包含查詢表單、表格和分頁功能。
 */
@Component({
  selector: 'app-cif001', // 組件的 CSS 選擇器
  standalone: true, // 表示這是獨立組件，不需要 NgModules
  // 導入所需的模組，包括 CommonModule、響應式表單模組和所有 Material 模組
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES],
  templateUrl: './cif001.html', // 組件的 HTML 模板文件路徑
  styleUrl: './cif001.css', // 組件的 CSS 樣式文件路徑
})
export class Cif001 implements OnInit {

  // 用於分頁器的變數，與 HTML 模板中的 MatPaginator 綁定
  totalItems: number = 0;   // 總項目數 (對應 HTML [length])
  pageSize: number = 5;     // 每頁顯示的項目數 (對應 HTML [pageSize])
  pageIndex: number = 0;    // 當前頁碼 (對應 HTML [pageIndex])

  searchForm: FormGroup; // 查詢表單的 FormGroup 實例

  // 定義表格中要顯示的列，順序與 HTML 中的 matColumnDef 綁定一致
  displayedColumns: string[] = [
    'idNum',        // 身份證字號
    'chineseName',  // 中文姓名
    'gender',       // 性別
    'education',    // 學歷
    'mobile',       // 行動電話
    'email',        // 電子郵件
    'year',         // 居住年限
    'actions'       // 操作 (例如修改按鈕)
  ];

  // MatTableDataSource 用於綁定數據到 Angular Material 表格
  dataSource = new MatTableDataSource<Data>([]);

  /**
   * 構造函數，注入所需的服務。
   * @param fb FormBuilder 服務，用於構建響應式表單。
   * @param dialog MatDialog 服務，用於打開 Material 對話框。
   * @param snackBar MatSnackBar 服務，用於顯示消息提示。
   * @param customerService CustomerService 服務，用於與後端 API 交互。
   */
  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private customerService: CustomerService
  ) {
    // 初始化查詢表單的 FormGroup 及其控制項，設置默認值
    this.searchForm = this.fb.group({
      idNum: [''],          // 身份證字號
      chineseName: [''],    // 中文姓名
      gender: [''],         // 性別
      education: [''],      // 學歷
      mobile: [''],         // 行動電話
      email: [''],          // 電子郵件
      residenceYears: [0]   // 現居年限，默認為 0
    });
  }

  /**
   * Angular 的生命週期鉤子，在組件初始化時調用。
   * 在此處調用 loadData() 來載入初始數據。
   */
  ngOnInit(): void {
    this.loadData();
  }

  /**
   * 處理查詢按鈕點擊事件。
   * 重置頁碼為第一頁，並重新載入數據。
   */
  onSearch(): void {
    this.pageIndex = 0; // 查詢時回到第一頁
    this.loadData(this.pageIndex, this.pageSize); // 載入數據
  }

  /**
   * 處理清除按鈕點擊事件。
   * 重置查詢表單，並重新執行查詢。
   */
  onClear(): void {
    // 重置表單，並將 residenceYears 設置回默認值 0
    this.searchForm.reset({ residenceYears: 0 });
    this.onSearch(); // 重新執行查詢以顯示所有數據
  }

  /**
   * 從後端載入客戶數據。
   * @param pageIdx 當前頁碼 (默認為 0，即第一頁)。
   * @param pageSize 每頁顯示的筆數 (默認為 5)。
   */
  loadData(pageIdx: number = 0, pageSize: number = 5) {
    // 1. 更新本地分頁變數，使 HTML 分頁器同步顯示正確的頁碼和每頁筆數
    this.pageIndex = pageIdx;
    this.pageSize = pageSize;

    // 2. 呼叫 API 服務來獲取數據。
    // 注意：後端 API 的頁碼通常從 1 開始，所以需要將本地的 pageIndex (從 0 開始) 加 1。
    const apiPageNumber = (this.pageIndex || 0) + 1;

    console.log(`查詢: 第 ${apiPageNumber} 頁, 每頁 ${this.pageSize} 筆`);

    // 調用 customerService 的 listCustomer 方法發送請求
    this.customerService.listCustomer(apiPageNumber, this.pageSize).subscribe({
      next: (res: any) => {
        // 檢查響應是否有效
        if (res && res.TRANRS) {
          // 將從 API 獲取的數據賦值給表格的數據源
          this.dataSource.data = res.TRANRS.items || [];
          // 更新總項目數，用於分頁器顯示總頁數
          this.totalItems = res.TRANRS.totalCount || 0;
          console.log('資料載入成功，總筆數:', this.totalItems);
        } else {
          // 如果響應無效，清空數據源並設置總項目數為 0
          this.dataSource.data = [];
          this.totalItems = 0;
        }
      },
      error: (err) => {
        // 處理 API 請求錯誤
        console.error('API 錯誤', err);
        // 清空數據源並設置總項目數為 0
        this.dataSource.data = [];
        this.totalItems = 0;
        // 可以在此處顯示錯誤消息給用戶
      }
    });
  }

  /**
   * 處理分頁器切換頁面事件。
   * 當用戶點擊分頁器按鈕時觸發，重新載入該頁的數據。
   * @param event PageEvent 對象，包含當前頁碼 (pageIndex) 和每頁筆數 (pageSize)。
   */
  onPageChange(event: PageEvent) {
    this.loadData(event.pageIndex, event.pageSize); // 載入新頁的數據
  }

  /**
   * 顯示一個 Material SnackBar (消息提示)。
   * @param message 要顯示的消息內容。
   * @param panelClass 應用於 SnackBar 的 CSS 類別，用於自定義樣式。
   */
  private showToast(message: string, panelClass: string) {
    this.snackBar.open(message, '✕', {
      duration: 3000, // 消息顯示的持續時間 (毫秒)
      horizontalPosition: 'left', // 水平位置
      verticalPosition: 'bottom', // 垂直位置
      panelClass: [panelClass] // 自定義樣式類別
    });
  }
}
