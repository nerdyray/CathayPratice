import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CustomerService } from './../../../services/customerService';

// Angular Material Modules
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSliderModule } from '@angular/material/slider';
import { MatSidenavContainer, MatSidenav, MatSidenavContent } from "@angular/material/sidenav";
import { MatNavList } from "@angular/material/list";
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
 * Edit001 組件
 * 負責編輯現有客戶的資料。
 */
@Component({
  selector: 'app-edit001',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule, ...MATERIAL_MODULES, MatSidenavContainer, MatSidenav, MatNavList, MatSidenavContent],
  templateUrl: './edit001.html',
  styleUrl: './edit001.css',
})
export class Edit001 implements OnInit {

  // 編輯表單的 FormGroup 實例
  editForm: FormGroup;

  // 注意：此為範例用的模擬數據，實際數據應從路由狀態中獲取。
  backendData = {
    "orderId": 43,
    "idNum": "A123459999",
    "chineseName": "測測",
    "gender": "m",
    "education": "大學",
    "zipCode1": "100",
    "address1": "台北市中正區忠孝東路一段1號",
    "telephone1": "02-23456789",
    "zipCode2": "100",
    "address2": "台北市中正區忠孝東路一段1號",
    "telephone2": "02-23456789",
    "mobile": "0912345678",
    "email": "ming@example.com",
    "year": 5
  };

  /**
   * 組件的構造函數
   * @param fb FormBuilder 服務，用於創建表單
   * @param router Router 服務，用於導航
   * @param customerService 客戶服務，用於 API 請求
   * @param snackBar MatSnackBar 服務，用於顯示提示訊息
   */
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private customerService: CustomerService,
    private snackBar: MatSnackBar
  ) {
    // 初始化表單結構，欄位名與後端數據結構對應
    this.editForm = this.fb.group({
      orderId: [''],
      idNum: [{ value: '', disabled: true }, Validators.required], // 身份證號碼設為禁用，不可修改
      chineseName: ['', Validators.required],
      gender: [''],
      education: [''],
      zipCode1: [''],
      address1: [''],
      telephone1: [''],
      zipCode2: [''],
      address2: [''],
      telephone2: [''],
      sameAsAddress1: [false], // 此為前端 UI 控制用，不屬於後端數據
      sameAsTelephone1: [false], // 此為前端 UI 控制用，不屬於後端數據
      mobile: ['', Validators.required],
      email: ['', Validators.email],
      year: [0]
    });
  }

  /**
   * Angular 生命週期鉤子，在組件初始化時執行。
   */
  ngOnInit(): void {
    // 步驟 1: 從路由的 history.state 中獲取從列表頁傳遞過來的客戶數據
    const navigation = window.history.state;
    const data = navigation.customerData;

    // 如果成功獲取到數據
    if (data) {
      // 步驟 2: 使用 patchValue 將數據填充到表單中。
      // patchValue 會自動匹配物件中與表單控件名稱相同的屬性。
      this.editForm.patchValue(data);
    } else {
      // 如果沒有獲取到數據 (例如用戶直接刷新了編輯頁面)，在控制台發出警告。
      console.warn('無傳入資料，可能需要重新查詢或返回列表');
    }

    // --- 表單內欄位的聯動邏輯 ---

    // 監聽 'sameAsAddress1' 勾選框的變化
    this.editForm.get('sameAsAddress1')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 如果勾選，將戶籍地址的值同步到現居地址
        this.editForm.patchValue({
          zipCode2: this.editForm.get('zipCode1')?.value,
          address2: this.editForm.get('address1')?.value
        }, { emitEvent: false }); // emitEvent: false 避免觸發無窮迴圈
      }
    });

    // 當戶籍地址變動時，如果勾選框被選中，則同步更新現居地址
    this.editForm.get('address1')?.valueChanges.subscribe(() => {
      if (this.editForm.get('sameAsAddress1')?.value) {
        this.editForm.patchValue({
          address2: this.editForm.get('address1')?.value
        }, { emitEvent: false });
      }
    });

    // 監聽 'sameAsTelephone1' 勾選框的變化
    this.editForm.get('sameAsTelephone1')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 如果勾選，將戶籍電話同步到現居電話
        this.editForm.patchValue({
          telephone2: this.editForm.get('telephone1')?.value
        }, { emitEvent: false });
      }
    });

    // 當戶籍電話變動時，如果勾選框被選中，則同步更新現居電話
    this.editForm.get('telephone1')?.valueChanges.subscribe(() => {
      if (this.editForm.get('sameAsTelephone1')?.value) {
        this.editForm.patchValue({
          telephone2: this.editForm.get('telephone1')?.value
        }, { emitEvent: false });
      }
    });
  }

  /**
   * 處理表單提交事件（儲存變更）。
   */
  onSubmit(): void {
    if (this.editForm.valid) {
      // 使用 getRawValue() 獲取表單的完整數據，包括被禁用的欄位 (如 idNum)
      const payload = this.editForm.getRawValue();
      this.customerService.editCustomer(payload).subscribe({
        next: (res) => {
          this.showToast('修改成功！', true);
          // 可選：成功後跳轉回列表頁
          // this.router.navigate(['/cif/list']);
        },
        error: (err) => {
          // 新增：處理 API 錯誤
          this.showToast('修改失敗，請稍後再試！', false);
          console.error('修改客戶失敗:', err);
        }
      });
    } else {
      // 如果表單無效，提示用戶檢查必填欄位
      this.showToast('請確認必填資料！', false);
    }
  }

  /**
   * 清除表單內容，但保留不可修改的身份證號碼。
   */
  onClear(): void {
    const currentId = this.editForm.getRawValue().idNum;
    this.editForm.reset();
    // 重置後，將身份證號碼和年限重新填入
    this.editForm.patchValue({ idNum: currentId, year: 0 });
  }

  /**
   * 導航回客戶列表頁面。
   */
  onReturn(): void {
    this.router.navigate(['/cif/list']); // 應與 app.routes.ts 中的路徑一致
  }

  /**
   * 顯示一個 SnackBar (Toast) 提示訊息。
   * @param message 要顯示的訊息
   * @param isSuccess 訊息類型是否為成功
   */
  showToast(message: string, isSuccess: boolean) {
    this.snackBar.open(message, '關閉', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: isSuccess ? ['success-snackbar'] : ['fail-snackbar']
    });
  }
}
