import { Data } from './../../../interface/T001Tranrq';
import { CustomerService } from './../../../services/customerService';
import { Q003Tranrq } from './../../../interface/Q003Tranrq';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // 建議加入 CommonModule
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
import { Router } from '@angular/router';
import { Customer } from '../../../interface/Customer';

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

@Component({
  selector: 'app-create001',
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES], // 修正 imports 語法
  templateUrl: './create001.html',
  styleUrl: './create001.css',
})
export class Create001 implements OnInit {


  createForm!: FormGroup;
  PATTERN_STRING_WITH_NUM: RegExp = /^[\u4e00-\u9fa5a-zA-Z0-9\s\-\.\,]+$/; // 驗證字串，允許中文、英文、數字和部分符號
  showErrorToast = false;   // 控制「錯誤」提示訊息的顯示
  showSuccessToast = false; // 控制「成功」提示訊息的顯示
  submited = false;         // 標記表單是否已送出
  isVerifying = false; // 用來控制按鈕 Loading 狀態 (選用)
  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private cdr: ChangeDetectorRef,          // 手動觸發變更偵測的工具
    private router: Router,                  // 路由服務，用來跳轉頁面
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.createForm = this.fb.group({
      idNum: ['', [Validators.required, Validators.pattern(/^[A-Z][12]\d{8}$/)]],
      chineseName: [{ value: '', disabled: true }, Validators.required],
      gender: [{ value: 'F', disabled: true }],
      education: [{ value: 'master', disabled: true }],

      zipCode_2: [{ value: '', disabled: true }, Validators.required],
      address1: [{ value: '', disabled: true }, Validators.required],
      telephone1: [{ value: '', disabled: true }, Validators.required],

      zipCode_1: [{ value: '103', disabled: true }],
      address2: [{ value: '', disabled: true }],
      telephone2: [{ value: '', disabled: true }],

      isSameAddress: [{ value: false, disabled: true }],
      isSamePhone: [{ value: false, disabled: true }],

      mobile: [{ value: '', disabled: true }, Validators.required],
      email: [{ value: '123@gmail.com', disabled: true }, [Validators.email]],
      year: [{ value: 0, disabled: true }, Validators.required]
    });



    // 1. 同戶籍地址
    this.createForm.get('isSameAddress')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 取得「戶籍地址」的值
        const sourceValue = this.createForm.get('address1')?.value;
        // 填入「現居地址」
        this.createForm.get('address2')?.setValue(sourceValue);
        // 選用：若勾選後想讓欄位唯讀，可加上 .disable()
      } else {
        this.createForm.get('address2')?.setValue('');
      }
    });

    // 2. 同戶籍電話
    this.createForm.get('isSamePhone')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 取得「戶籍電話」的值
        const sourceValue = this.createForm.get('telephone1')?.value;
        // 填入「現居電話」
        this.createForm.get('telephone2')?.setValue(sourceValue);
      } else {
        this.createForm.get('telephone2')?.setValue('');
      }
    });
  }

  // 驗證按鈕動作
  onVerify() {
    const idNum = this.createForm.get('idNum');
    if (idNum?.invalid) return;
    this.isVerifying = true;
    //呼叫Service
    this.customerService.checkId({ idNum: idNum?.value }).subscribe({
      next: (res) => {
        const returnCode = res.MWHEADER.RETURNCODE;
        if (returnCode === '0000') {
          // 回傳成功 = 資料已存在 = 驗證失敗(Duplicate)
          idNum?.setErrors({ duplicate: true });
          console.log('驗證失敗：資料已存在');
        } else {
          // 回傳其他代碼 (如 404, E001) = 查無資料 = 驗證通過
          idNum?.setErrors(null);
          this.createForm.enable();
          console.log('驗證成功：查無資料，可註冊');
        }
        this.isVerifying = false;
        this.cdr.detectChanges();
      }, error: (err) => {
        console.error('帳號已存在:', err); // 在開發者工具中印出錯誤，方便除錯
        this.showErrorToast = true;      // 顯示錯誤提示給使用者
        this.isVerifying = false;
      }
    })

  }

  onReset() {
    // 這裡的名稱也必須跟上方定義的一致
    this.createForm.reset();
  }

  onSubmit() {
    if (this.createForm.valid) {
      console.log('提交資料：', this.createForm.value);
    } else {
      this.createForm.markAllAsTouched();
      alert('表單有誤，請檢查紅色必填欄位');
    }
    const rawData = this.createForm.getRawValue();
    const customer: Data = { ...rawData };
    this.customerService.addCustomer(customer).subscribe({
      next: (res) => {
        this.showSuccessToast = true;
        this.cdr.detectChanges();
      }
    })
  }

  closeToast() {
    throw new Error('Method not implemented.');
  }
}
