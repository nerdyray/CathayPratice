import { Component, OnInit } from '@angular/core';
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
  standalone: true, // 確保是 standalone
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ...MATERIAL_MODULES], // 修正 imports 語法
  templateUrl: './create001.html',
  styleUrl: './create001.css',
})
export class Create001 implements OnInit {
  createForm!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.createForm = this.fb.group({
      idNumber: ['', [Validators.required]],
      name: ['', Validators.required],
      gender: ['F'], // 對應 HTML 的 value="F"
      education: ['master'],

      // --- 戶籍 (Permanent / Residence) ---
      resZip: ['', Validators.required],     // HTML: formControlName="resZip"
      resAddress: ['', Validators.required], // HTML: formControlName="resAddress"
      resPhone: ['', Validators.required],   // HTML: formControlName="resPhone"

      // --- 現居 (Current) ---
      curZip: ['103'],                       // HTML: formControlName="curZip"
      curAddress: [''],                      // HTML: formControlName="curAddress"
      curPhone: [''],                        // HTML: formControlName="curPhone"

      // --- 核取方塊 ---
      isSameAddress: [false],
      isSamePhone: [false],

      // --- 其他 ---
      mobile: ['', Validators.required],
      email: ['123@gmail.com', [Validators.email]],
      livingYears: [0, Validators.required]  // HTML: formControlName="livingYears"
    });

    // --- 監聽邏輯 (必須放在 initForm 內部) ---

    // 1. 同戶籍地址
    this.createForm.get('isSameAddress')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 取得「戶籍地址」的值
        const sourceValue = this.createForm.get('resAddress')?.value;
        // 填入「現居地址」
        this.createForm.get('curAddress')?.setValue(sourceValue);
        // 選用：若勾選後想讓欄位唯讀，可加上 .disable()
      } else {
        this.createForm.get('curAddress')?.setValue('');
      }
    });

    // 2. 同戶籍電話
    this.createForm.get('isSamePhone')?.valueChanges.subscribe(checked => {
      if (checked) {
        // 取得「戶籍電話」的值
        const sourceValue = this.createForm.get('resPhone')?.value;
        // 填入「現居電話」
        this.createForm.get('curPhone')?.setValue(sourceValue);
      } else {
        this.createForm.get('curPhone')?.setValue('');
      }
    });
  }

  // 驗證按鈕動作
  onVerify() {
    const id = this.createForm.get('idNumber')?.value;
    if (id) {
      console.log('正在驗證身分證字號:', id);
    }
  }

  onReset() {
    // 這裡的名稱也必須跟上方定義的一致
    this.createForm.reset({
      gender: 'F',
      education: 'master',
      curZip: '103',
      email: '123@gmail.com',
      livingYears: 0,
      isSameAddress: false,
      isSamePhone: false
    });
  }

  onSubmit() {
    if (this.createForm.valid) {
      console.log('提交資料：', this.createForm.value);
    } else {
      this.createForm.markAllAsTouched(); // 強制顯示紅字錯誤
      alert('表單有誤，請檢查紅色必填欄位');
    }
  }
}
