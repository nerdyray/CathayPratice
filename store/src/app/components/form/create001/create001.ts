import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Store } from '../../../interface/store';


@Component({
  selector: 'app-create001',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './create001.html',
  styleUrl: './create001.css',
})
export class Create001 implements OnInit {

  //錯誤訊息彈出
  showErrorToast: boolean = false;
  //傳入商店資料
  @Input() set storeData(value: Store | null) {
    if (value) {
      this.storeForm.patchValue(value);
    }
  }
  //新增資料觸發事件
  @Output() addStore = new EventEmitter<Store>();
  //修改資料觸發事件
  @Output() updateStore = new EventEmitter<Store>();
  //刪除資料觸發事件
  @Output() deleteStore = new EventEmitter<Store>();
  //查詢資料觸發事件
  @Output() searchStore = new EventEmitter<Store>();
  //建立商店FormFroup
  storeForm!: FormGroup;
  //送出確認
  submited: boolean = false;
  //價格正則表示式
  PATTERN_TEL: RegExp = (/^[0-9]{1,15}$/)
  //文字正則表示式
  PATTERN_STRING: RegExp = (/^[\u4e00-\u9fa5a-zA-Z]+$/)
  //數字正則表示式
  PATTERN_NUMBER: string = '^[1-9][0-9]*$'
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // TODO: 練習區 - 請在此處定義你的表單控制項與驗證邏輯
    this.storeForm = this.fb.group({
      storeName: ['', [Validators.compose([
        Validators.required, Validators.minLength(1), Validators.maxLength(10), Validators.pattern(this.PATTERN_STRING)
      ])]],
      tel: ['', [Validators.compose([Validators.required, Validators.pattern(this.PATTERN_TEL)])]],
      owner: ['', [Validators.compose([Validators.required, Validators.pattern(this.PATTERN_STRING)])]],
      // 2. 選填欄位
      fax: ['', [Validators.compose([Validators.required, Validators.pattern(this.PATTERN_NUMBER)])]],
      mobile: ['', [Validators.compose([Validators.required, Validators.pattern(this.PATTERN_NUMBER)])]],
      address: ['', [Validators.compose([Validators.required, Validators.pattern(this.PATTERN_STRING)])]],

      // 3. 特殊欄位 (評價、日期)
      rating: ['讚'], // 預設值
      updateDate: [{ value: '2022-05-16', disabled: true }] // 設定 disabled 狀態
    });
  }

  // 按鈕觸發函式
  onAdd(): void {
    this.storeForm.markAllAsTouched();
    if (this.storeForm.invalid) {
      this.showErrorToast = true;
      setTimeout(() => {
        this.showErrorToast = false;
      }, 3000);
      return;
    }
    this.submited = true;
    const rawData = this.storeForm.getRawValue();
    const store: Store = { ...rawData };
    this.addStore.emit(store);
    console.log('表單狀態:', this.storeForm.status);
    console.log('表單數值:', this.storeForm.getRawValue()); // getRawValue 包含 disabled 的欄位

    // TODO: 練習區 - 實作提交檢查
    // if (this.storeForm.invalid) { ... }
  }

  onClear(): void {
    // TODO: 練習區 - 實作重置邏輯
    // this.storeForm.reset(...);
  }

  onBack(): void {
    console.log('返回上一頁');
  }

  // Helper function (給 HTML 用來移除評價 tag)
  removeRating(): void {
    // TODO: 練習區 - 清除 rating 欄位的值
    // this.storeForm.get('rating')?.setValue(null);
  }
  closeToast(): void {
    this.showErrorToast = false;
  }
}
