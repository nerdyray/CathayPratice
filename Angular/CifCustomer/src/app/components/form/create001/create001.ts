import { MWHEADER } from './../../../interface/Q003Tranrs';
// 導入與 T001Tranrq 接口相關的 Data 類型，用於新增客戶請求的數據結構
import { Data } from './../../../interface/T001Tranrq';
// 導入 CustomerService，用於與後端 API 互動
import { CustomerService } from './../../../services/customerService';
// 導入 Q003Tranrq，用於身份證驗證請求的數據結構
import { Q003Tranrq } from './../../../interface/Q003Tranrq';
// 導入 Angular 核心模組
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
// 導入 Angular 表單相關模組
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
// 導入 Angular 路由模組，用於頁面導航
import { Router, RouterLink } from '@angular/router';
// 導入 MatSnackBar，用於顯示提示訊息
import { MatSnackBar } from '@angular/material/snack-bar';
import { delay, takeUntil } from 'rxjs';

// 將所有用到的 Angular Material 模組集合到一個常數中，方便管理
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
 * Create001 組件
 * 負責處理新增客戶資料的表單功能，包括表單的初始化、驗證、與後端服務的數據提交。
 */
@Component({
  selector: 'app-create001',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES],
  templateUrl: './create001.html',
  styleUrl: './create001.css',
})
export class Create001 implements OnInit {

  // 響應式表單的 FormGroup 實例
  createForm!: FormGroup;
  // 用於驗證字串的正則表達式，允許中文、英文、數字和部分符號
  PATTERN_STRING_WITH_NUM: RegExp = /^[\u4e00-\u9fa5a-zA-Z0-9\s\-\.\,]+$/;
  // 標記表單是否已送出，用於控制 UI 狀態
  submited = false;
  // 控制「身份證驗證」按鈕的加載狀態
  isVerifying = false;

  /**
   * 組件的構造函數
   * @param fb FormBuilder 服務，用於輕鬆創建 FormGroup 和 FormControl。
   * @param customerService 客戶服務，用於調用後端 API。
   * @param cdr ChangeDetectorRef 服務，用於手動觸發 Angular 的變更偵測。
   * @param router Router 服務，用於處理導航。
   * @param snackBar MatSnackBar 服務，用於顯示提示訊息。
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
   */
  ngOnInit(): void {
    // 初始化表單結構和驗證規則
    this.initForm();
  }

  /**
   * 初始化響應式表單 (createForm) 的結構和驗證規則。
   */
  initForm() {
    this.createForm = this.fb.group({
      // 身份證字號：必填，並符合台灣身份證的正則表達式格式
      idNum: ['', [Validators.required, Validators.pattern(/^[A-Z][12]\d{8}$/)]],
      // 中文姓名：必填，初始狀態為禁用
      chineseName: [{ value: '', disabled: true }, Validators.required],
      // 性別：初始禁用，默認為 'F' (女)
      gender: [{ value: 'f', disabled: true }],
      // 學歷：初始禁用，默認為 'master' (碩士)
      education: [{ value: 'master', disabled: true }],
      // 戶籍地址相關欄位
      zipCode1: [{ value: '', disabled: true }, Validators.required],
      address1: [{ value: '', disabled: true }, Validators.required],
      telephone1: [{ value: '02', disabled: true }, [Validators.required, Validators.minLength(1), Validators.maxLength(10)]],
      // 現居地址相關欄位
      zipCode2: [{ value: '', disabled: true }, Validators.required],
      address2: [{ value: '', disabled: true }],
      telephone2: [{ value: '02', disabled: true }],
      // 「同戶籍地址/電話」的勾選框
      isSameAddress: [{ value: false, disabled: true }],
      isSamePhone: [{ value: false, disabled: true }],
      // 行動電話：必填，且長度為 10
      mobile: [{ value: '09', disabled: true }, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      // 電子郵件：需要符合 email 格式
      email: [{ value: '123@gmail.com', disabled: true }, [Validators.email]],
      // 現居年限：必填
      year: [{ value: 0, disabled: true }, Validators.required]
    });

    // --- 表單內欄位的聯動邏輯 ---

    // 監聽 'isSameAddress' 勾選框的變化
    this.createForm.get('isSameAddress')?.valueChanges.subscribe(checked => {
      if (checked) {
        this.createForm.get('address2')?.disable();
        // 如果勾選，將戶籍地址的值同步到現居地址
        const address1Value = this.createForm.get('address1')?.value;
        const zipCode1Value = this.createForm.get('zipCode1')?.value;
        this.createForm.patchValue({
          address2: address1Value,
          zipCode2: zipCode1Value
        }, { emitEvent: false }); // emitEvent: false 避免觸發無窮迴圈
      } else {
        this.createForm.get('address2')?.enable();

      }
    });

    // 當戶籍地址變動時，如果「同戶籍地址」被勾選，則同步更新現居地址
    this.createForm.get('address1')?.valueChanges.subscribe(val => {
      if (this.createForm.get('isSameAddress')?.value) {
        this.createForm.get('address2')?.setValue(val, { emitEvent: false });
      }
    });
    this.createForm.get('zipCode1')?.valueChanges.subscribe(val => {
      if (this.createForm.get('isSameAddress')?.value) {
        this.createForm.get('zipCode2')?.setValue(val, { emitEvent: false });
      }
    });

    // 監聽 'isSamePhone' 勾選框的變化
    this.createForm.get('isSamePhone')?.valueChanges.subscribe(checked => {
      if (checked) {
        this.createForm.get('telephone2')?.disable();
        // 如果勾選，將戶籍電話的值同步到現居電話
        const telephone1Value = this.createForm.get('telephone1')?.value;
        this.createForm.get('telephone2')?.setValue(telephone1Value, { emitEvent: false });
      } else {
        this.createForm.get('telephone2')?.enable();

      }
    });
    this.createForm.get('telephone1')?.valueChanges.subscribe(val => {
      if (this.createForm.get('isSamePhone')?.value) {
        this.createForm.get('telephone2')?.setValue(val, { emitEvent: false });
      }
    });
    this.createForm.get('idNum')?.valueChanges.subscribe(() => {
      if (this.createForm.get('idNum')?.invalid) {
        this.isVerifying = true;
      } else {
        this.isVerifying = false;
      }
    });
  }
  /**
   * 處理身份證字號的驗證。
   * 調用後端服務檢查該身份證號碼是否已存在於資料庫中。
  */
  onVerify() {
    const idNum = this.createForm.get('idNum');
    // 如果身份證號碼本身格式無效，則不執行後續操作
    // if (idNum?.invalid) {
    //   this.isVerifying = true; // 顯示加載狀態
    //   return;
    // }
    // 調用後端 API 進行驗證
    this.customerService.checkId({ idNum: idNum?.value }).subscribe({
      next: (res) => {
        const returnCode = res.MWHEADER.RETURNCODE;
        // 如果後端返回非 '0000'，表示可註冊
        if (returnCode !== '0000') {
          this.createForm.enable(); // 啟用表單所有欄位
          this.createForm.get('idNum')?.disable(); // 鎖定已驗證的身份證號碼欄位
          idNum?.setErrors(null); // 清除可能存在的錯誤狀態
          this.showToast('身分證不存在可以註冊', true);
          this.isVerifying = true; // 結束加載狀態
        } else {
          // 如果返回 '0000'，表示資料已存在
          idNum?.setErrors({ duplicate: true }); // 設置一個 'duplicate' 錯誤
          this.showToast('資料已存在', false);
        }
        this.cdr.detectChanges(); // 手動觸發變更檢測以更新 UI
      },
      error: (err) => {
        // 處理 API 調用失敗的情況
        this.showToast('驗證失敗，請稍後再試', false);
        this.isVerifying = false; // 結束加載狀態
      }
    })
  }

  /**
   * 重置整個表單回到初始狀態。
   */
  onReset() {
    this.createForm.reset();
  }

  /**
   * 處理表單的最終提交。
   * 檢查表單有效性，如果有效則提交數據，否則顯示錯誤提示。
   */
  onSubmit() {
    // 步驟 1：檢查整個表單的有效性
    if (this.createForm.invalid) {
      this.showToast('請確認必填資料！', false);
      return; // 終止提交
    }
    this.createForm.markAllAsTouched();

    // 步驟 2：獲取表單的原始數據 (包括被禁用的欄位)
    const rawData = this.createForm.getRawValue();
    // 步驟 3：調用後端服務提交數據
    this.customerService.addCustomer(rawData).subscribe({
      next: (res) => {
        if (res.MWHEADER.RETURNCODE === '0000') {
          // API 請求成功後的回調

          this.showToast('新增成功', true)
          this.cdr.detectChanges();
          this.onReset();
          this.isVerifying = true;
        } else {
          this.showToast('新增失敗，請稍後再試', false);
          console.error('新增客戶失敗:');
        }
      },
      error: (err) => {
        // API 請求失敗後的回調
        this.showToast('新增失敗，請稍後再試', false);
        console.error('新增客戶失敗:', err);
      }
    });

  }

  /**
   * 顯示一個 SnackBar (Toast) 提示訊息。
   * @param message 要顯示的訊息
   * @param isSuccess 訊息類型是否為成功 (true: 成功, false: 失敗)
   */
  showToast(message: string, isSuccess: boolean) {
    this.snackBar.open(message, '關閉', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: isSuccess ? ['success-snackbar'] : ['fail-snackbar'] // 根據成功或失敗應用不同的 CSS class
    });
  }

}
function takeUntilDestroyed(): import("rxjs").OperatorFunction<any, unknown> {
  throw new Error('Function not implemented.');
}

