import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';

// 導入 Angular Material 相關模組
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { MatRadioButton, MatRadioModule } from "@angular/material/radio";
import { MatSlider, MatSliderModule } from "@angular/material/slider";
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { Q002Tranrq, Q002TranrqPage, Q002TranrqSortInfo } from '../../../interface/Q002Tranrq';
import { CustomerService } from '../../../services/customerService';
import { MatSnackBar } from '@angular/material/snack-bar';

// 將所有用到的 Angular Material 模組集合到一個常數中，方便管理
const MATERIAL_MODULES = [
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatRadioModule,
  MatButtonModule,
  MatIconModule,
  MatCardModule,
  MatCheckboxModule,
  MatSliderModule
];

/**
 * Search001 組件
 * 一個可重用的搜尋表單組件，用於提供客戶資料的篩選條件。
 * 當用戶點擊「查詢」時，它會將表單的當前值作為事件發射給父組件。
 */
@Component({
  selector: 'app-search001',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule, ...MATERIAL_MODULES, MatRadioButton, MatSlider],
  templateUrl: './search001.html',
  styleUrls: ['./search001.css'],
})
export class Search001 implements OnInit {
  page: Q002TranrqPage = {
    pageNumber: 1,
    pageSize: 5
  }
  sortInfo: Q002TranrqSortInfo = {
    sortBy: 'asc',
    sortColumn: 'idNum'
  }

  // 搜尋表單的 FormGroup 實例
  searchForm: FormGroup

  // @Output() 裝飾器創建一個事件發射器，用於將數據從子組件傳遞到父組件。
  // 當 onSearch 被調用時，會發射 searchEvent 事件，並攜帶表單的數據。
  @Output() searchEvent = new EventEmitter<any>();

  /**
   * 組件的構造函數
   * @param fb FormBuilder 服務，用於創建響應式表單。
   */
  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private snackBar: MatSnackBar,
  ) {
    // 初始化搜尋表單及其所有欄位
    this.searchForm = this.fb.group({
      idNum: [''],       // 身分證字號
      chineseName: [''], // 姓名
      gender: [''],      // 性別
      education: [''],   // 學歷
      mobile: [''],      // 手機
      email: [''],       // Email
      year: [null]       // 現居年限，初始為 null
    });
  }


  /**
   * Angular 生命週期鉤子，在組件初始化時調用。
   */
  ngOnInit(): void {



  }

  /**
   * 處理「查詢」按鈕的點擊事件。
   * 通過 searchEvent 將表單的當前值發射給父組件。
   */
  onSearch(): void {
    this.searchEvent.emit(this.searchForm.value);
  }

  /**
   * 處理「清除」按鈕的點擊事件。
   * 重置表單為其初始狀態（通常是空值），並立即觸發一次新的搜尋。
   * 這通常意味著父組件將收到一個空的搜尋條件對象，從而可以重新加載所有數據。
   */
  onClear(): void {
    // 重置表單
    this.searchForm.reset({
      idNum: '',
      chineseName: '',
      gender: '',
      education: '',
      mobile: '',
      email: '',
      year: null
    });


    // 清除後，立即觸發一次查詢事件，以便父組件更新列表
    this.onSearch();
  }

  
  /**
 * 顯示一個 SnackBar (Toast) 訊息。
 * @param message - 要顯示的訊息文字
 * @param panelClass - 用於控制樣式的 CSS class (例如 'success-snackbar', 'error-snackbar')
 */
  private showToast(message: string, panelClass: string) {
    this.snackBar.open(message, '關閉', {
      duration: 3000, // 3 秒後自動關閉
      panelClass: [panelClass]
    });
  }
}
