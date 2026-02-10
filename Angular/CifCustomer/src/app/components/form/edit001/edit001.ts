import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CustomerService } from './../../../services/customerService';

// Angular Material Modules (保持不變)
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
import { Router } from '@angular/router'; // 引入 Router 以便在儲存後跳回
import { MatSnackBar } from '@angular/material/snack-bar';

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

@Component({
  selector: 'app-edit001',
  standalone: true,
  imports: [RouterLink, CommonModule, ReactiveFormsModule, ...MATERIAL_MODULES, MatSidenavContainer, MatSidenav, MatNavList, MatSidenavContent],
  templateUrl: './edit001.html',
  styleUrl: './edit001.css',
})
export class Edit001 implements OnInit {

  editForm: FormGroup;

  // 模擬後端資料 (Key 直接對應)
  backendData = {
    "orderId": 43,
    "idNum": "A123459999",
    "chineseName": "測測",
    "gender": "m",
    "education": "大學",
    "zipCode_1": "100",
    "address1": "台北市中正區忠孝東路一段1號",
    "telephone1": "02-23456789",
    "zipCode_2": "100",
    "address2": "台北市中正區忠孝東路一段1號",
    "telephone2": "02-23456789",
    "mobile": "0912345678",
    "email": "ming@example.com",
    "year": 5
  };


  constructor(private fb: FormBuilder,
    private router: Router, // 注入 Router
    private customerService: CustomerService,
    private snackBar: MatSnackBar


  ) {
    // 1. 初始化表單：名稱直接使用後端 Json 欄位
    this.editForm = this.fb.group({
      orderId: [''],
      idNum: [{ value: '', disabled: true }, Validators.required],
      chineseName: ['', Validators.required],
      gender: [''],
      education: [''],

      // 戶籍 (1)
      zipCode_1: [''],
      address1: [''],
      telephone1: [''],

      // 現居 (2)
      zipCode_2: [''],
      address2: [''],
      telephone2: [''],

      // 勾選框 (純前端用，不傳後端)
      sameAsAddress1: [false],
      sameAsTelephone1: [false],

      mobile: ['', Validators.required],
      email: ['', Validators.email],
      year: [0] // 原本是 residenceYears，現在直接用 year
    });
  }

  ngOnInit(): void {
    // 1. 從路由狀態中取得資料
    const navigation = window.history.state;
    const data = navigation.customerData;


    if (data) {
      // 2. 載入資料：直接使用 patchValue
      // 因為 idNum 是 disabled，patchValue 依然可以把值填入並顯示，但使用者無法修改
      this.editForm.patchValue(data);
    } else {
      // 如果不是從列表頁過來的（例如直接重新整理頁面），可以選擇跳回列表或載入預設值
      console.warn('無傳入資料，可能需要重新查詢或返回列表');
    }
    // 監聽：同戶籍地址
    this.editForm.get('sameAsAddress1')?.valueChanges.subscribe(checked => {
      if (checked) {
        this.editForm.patchValue({
          zipCode_2: this.editForm.get('zipCode_1')?.value,
          address2: this.editForm.get('address1')?.value
        });
      }
    });

    // 監聽：同戶籍電話
    this.editForm.get('sameAsTelephone1')?.valueChanges.subscribe(checked => {
      if (checked) {
        this.editForm.patchValue({
          telephone2: this.editForm.get('telephone1')?.value
        });
      }
    });
  }

  /**
   * 儲存按鈕
   */
  onSubmit(): void {
    if (this.editForm.valid) {
      // 3. 取得資料：直接拿 RawValue (包含 disabled 的欄位)
      const payload = this.editForm.getRawValue();
      this.customerService.editeCustomer(payload).subscribe({
        next: (res) => {
          this.showToast('修改成功！', true);
        }
      })
    } else {
      this.showToast('請確認必填資料！', false);
    }
  }

  onClear(): void {
    const currentId = this.editForm.getRawValue().idNum;
    this.editForm.reset();
    this.editForm.patchValue({ idNum: currentId, year: 0 });
  }

  onReturn(): void {
    this.router.navigate(['/cif001']);
  }
  showToast(message: string, isSuccess: boolean) {
    this.snackBar.open(message, '關閉', {
      duration: 3000,               // 3 秒後自動關閉
      horizontalPosition: 'right',  // 顯示在右側
      verticalPosition: 'top',      // 顯示在上方
      panelClass: isSuccess ? ['success-snackbar'] : ['fail-snackbar'] // 顏色控制
    });
  }
}
