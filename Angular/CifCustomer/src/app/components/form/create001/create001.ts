// 導入與 T001Tranrq 接口相關的 Data 類型，用於新增客戶請求的數據結構
import { Data } from './../../../interface/T001Tranrq';
// 導入 CustomerService，用於與後端 API 互動
import { CustomerService } from './../../../services/customerService';
// 導入 Q003Tranrq，用於身份證驗證請求的數據結構
import { Q003Tranrq } from './../../../interface/Q003Tranrq';
// 導入 Angular 核心模組中的 ChangeDetectorRef (變更偵測) 和 Component (組件裝飾器)、OnInit (生命週期鉤子)
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
// 導入 Angular 表單模組中的 FormBuilder (用於構建響應式表單)、FormGroup (表單組)、Validators (驗證器)、ReactiveFormsModule 和 FormsModule
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
// 導入 CommonModule，提供常用指令如 ngIf, ngFor
import { CommonModule } from '@angular/common';
// 導入 Angular Material 相關模組
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatSliderModule } from '@angular/material/slider';
// 導入 Angular 路由模組中的 Router，用於頁面導航
import { Router } from '@angular/router';
// 導入 Customer 接口，定義客戶數據的結構
import { Customer } from '../../../interface/Customer';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

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
  MatSliderModule
];

/**
 * Create001 組件，用於客戶資料的新增功能。
 * 處理表單的初始化、驗證、數據提交以及與後端服務的交互。
 */
@Component({
  selector: 'app-create001', // 組件的 CSS 選擇器
  standalone: true, // 表示這是獨立組件，不需要 NgModules
  // 導入所需的模組，包括 CommonModule、響應式表單模組和所有 Material 模組
  imports: [RouterLink, CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES],
  templateUrl: './create001.html', // 組件的 HTML 模板文件路徑
  styleUrl: './create001.css', // 組件的 CSS 樣式文件路徑
})
export class Create001 implements OnInit {

  createForm!: FormGroup; // 聲明表單組，用於管理表單控制項和其狀態
  // 正則表達式，用於驗證字串，允許中文、英文、數字和部分符號
  PATTERN_STRING_WITH_NUM: RegExp = /^[\u4e00-\u9fa5a-zA-Z0-9\s\-\.\,]+$/;
  showErrorToast = false;   // 控制「錯誤」提示訊息的顯示狀態
  showSuccessToast = false; // 控制「成功」提示訊息的顯示狀態
  submited = false;         // 標記表單是否已送出
  isVerifying = false; // 用來控制身份證驗證按鈕的 Loading 狀態

  /**
   * 構造函數，注入所需的服務。
   * @param fb FormBuilder 服務，用於輕鬆創建 FormGroup 和 FormControl。
   * @param customerService 客戶服務，用於調用後端 API。
   * @param cdr ChangeDetectorRef 服務，用於手動觸發 Angular 的變更偵測。
   * @param router Router 服務，用於處理導航。
   */
  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private cdr: ChangeDetectorRef,
    private router: Router,
    private snackBar: MatSnackBar
  ) { }

  /**
   * Angular 的生命週期鉤子，在組件初始化時調用。
   * 在此處調用 initForm() 來初始化表單。
   */
  ngOnInit(): void {
    this.initForm();
  }

  /**
   * 初始化響應式表單 (createForm) 的結構和驗證規則。
   */
  initForm() {
    this.createForm = this.fb.group({
      // 身份證字號：必填，並符合特定的正則表達式格式
      idNum: ['', [Validators.required, Validators.pattern(/^[A-Z][12]\d{8}$/)]],
      // 中文姓名：初始禁用，必填
      chineseName: [{ value: '', disabled: true }, Validators.required],
      // 性別：初始禁用，默認為 'F' (女)
      gender: [{ value: 'F', disabled: true }],
      // 學歷：初始禁用，默認為 'master' (碩士)
      education: [{ value: 'master', disabled: true }],

      // 戶籍地址相關欄位：初始禁用，必填
      zipCode_2: [{ value: '', disabled: true }, Validators.required],
      address1: [{ value: '', disabled: true }, Validators.required],
      telephone1: [{ value: '', disabled: true }, Validators.required, Validators.minLength(10),
      Validators.maxLength(10)],

      // 現居地址相關欄位：初始禁用
      zipCode_1: [{ value: '', disabled: true }],
      address2: [{ value: '', disabled: true }],
      telephone2: [{ value: '', disabled: true }, Validators.minLength(10),
      Validators.maxLength(10)],

      // 「同戶籍地址/電話」的勾選框：初始禁用，默認為 false
      isSameAddress: [{ value: false, disabled: true }],
      isSamePhone: [{ value: false, disabled: true }],

      // 行動電話：初始禁用，必填
      mobile: [{ value: '', disabled: true }, Validators.required, Validators.minLength(10),
      Validators.maxLength(10)],
      // 電子郵件：初始禁用，默認為 '123@gmail.com'，並進行 Email 格式驗證
      email: [{ value: '123@gmail.com', disabled: true }, [Validators.email]],
      // 現居年限：初始禁用，默認為 0，必填
      year: [{ value: 0, disabled: true }, Validators.required]
    });

    // 訂閱 'isSameAddress' 欄位的變化，實現「同戶籍地址」的聯動邏輯
    this.createForm.get('isSameAddress')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 如果勾選，將戶籍地址的值設置到現居地址
        const sourceValue = this.createForm.get('address1')?.value;
        this.createForm.get('address2')?.setValue(sourceValue);
        // 可以選擇在此處禁用現居地址欄位：this.createForm.get('address2')?.disable();
      } else {
        // 如果取消勾選，清空現居地址
        this.createForm.get('address2')?.setValue('');
        // 可以選擇在此處啟用現居地址欄位：this.createForm.get('address2')?.enable();
      }
    });

    // 訂閱 'isSamePhone' 欄位的變化，實現「同戶籍電話」的聯動邏輯
    this.createForm.get('isSamePhone')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 如果勾選，將戶籍電話的值設置到現居電話
        const sourceValue = this.createForm.get('telephone1')?.value;
        this.createForm.get('telephone2')?.setValue(sourceValue);
      } else {
        // 如果取消勾選，清空現居電話
        this.createForm.get('telephone2')?.setValue('');
      }
    });
  }

  /**
   * 處理身份證字號的驗證動作。
   * 調用 CustomerService 來檢查身份證字號是否已存在。
   */
  onVerify() {
    const idNum = this.createForm.get('idNum');
    // 如果身份證字號無效，則直接返回
    if (idNum?.invalid) return;

    this.isVerifying = true; // 設置驗證狀態為進行中

    // 呼叫 CustomerService 的 checkId 方法來驗證身份證字號
    this.customerService.checkId({ idNum: idNum?.value }).subscribe({
      next: (res) => {
        const returnCode = res.MWHEADER.RETURNCODE;
        if (returnCode === '0000') {
          // 如果後端返回 '0000'，表示資料已存在，驗證失敗 (重複)
          idNum?.setErrors({ duplicate: true });
          this.showToast('資料已存在', false);
        } else {
          // 如果返回其他代碼 (例如 404, E001)，表示查無資料，驗證通過
          idNum?.setErrors(null); // 清除所有錯誤
          this.createForm.enable(); // 啟用整個表單供用戶輸入
          this.showToast('身分證不存在可以註冊', true);
        }
        this.isVerifying = false; // 驗證結束，設置驗證狀態為非進行中
        this.cdr.detectChanges(); // 手動觸發變更偵測，更新 UI
      },
      error: (err) => {
        // 處理 API 錯誤
        this.showErrorToast = true;      // 顯示錯誤提示給使用者
        this.isVerifying = false; // 驗證結束，設置驗證狀態為非進行中
      }
    })
  }

  /**
   * 重置表單為初始狀態。
   */
  onReset() {
    this.createForm.reset();
  }

  /**
   * 處理表單提交動作。
   * 檢查表單有效性，如果有效則提交數據，否則顯示錯誤提示。
   */
  onSubmit() {
    if (this.createForm.valid) {
      this.showToast('新增成功', true);
    } else {
      // 如果表單無效，將所有控制項標記為 touched，以觸發錯誤訊息顯示
      this.createForm.markAllAsTouched();
      this.showToast('請確認必填資料！', false);
    }
    // 獲取表單的原始值 (包括禁用的控制項)
    const rawData = this.createForm.getRawValue();
    // 將原始數據映射到客戶數據接口
    const customer: Data = { ...rawData };
    // 呼叫 CustomerService 的 addCustomer 方法來新增客戶
    this.customerService.addCustomer(customer).subscribe({
      next: (res) => {
        this.showSuccessToast = true; // 顯示成功提示訊息
        this.cdr.detectChanges(); // 手動觸發變更偵測，更新 UI
      }
    })
  }
  // 顯示訊息的方法
  showToast(message: string, isSuccess: boolean) {
    this.snackBar.open(message, '關閉', {
      duration: 3000,               // 3 秒後自動關閉
      horizontalPosition: 'right',  // 顯示在右側
      verticalPosition: 'top',      // 顯示在上方
      panelClass: isSuccess ? ['success-snackbar'] : ['fail-snackbar'] // 顏色控制
    });
  }

}
