import { StoreService } from './../../../services/storeService'; // 路徑請確認
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Store } from '../../../interface/store';

@Component({
  selector: 'app-create001',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './create001.html',
  styleUrl: './create001.css',
})
export class Create001 implements OnInit {

  remarksOptions: string[] = ['好', '中', '壞'];

  // 修正 1: Regex 調整
  // 電話: 允許 0-9，長度 1-15 (原本的寫法正確)
  PATTERN_TEL: RegExp = /^[0-9]{1,15}$/;
  // 文字: 允許中文、英文、數字 (增加 0-9 避免地址或店名報錯)
  PATTERN_STRING_WITH_NUM: RegExp = /^[\u4e00-\u9fa5a-zA-Z0-9\s\-\.\,]+$/;
  // 純數字: 用於傳真或手機 (修正：改用上面的 TEL Pattern 即可，不需要這個不允許0開頭的 Pattern)
  // PATTERN_NUMBER: string = '^[1-9][0-9]*$' // 刪除這行，這會害死手機號碼

  storeForm!: FormGroup;
  showErrorToast: boolean = false;
  showSuccessToast: boolean = false;
  submited: boolean = false;

  @Output() addStore = new EventEmitter<Store>();
  @Output() updateStore = new EventEmitter<Store>();
  @Output() deleteStore = new EventEmitter<Store>();
  @Output() searchStore = new EventEmitter<Store>();

  constructor(
    private fb: FormBuilder,
    private storeService: StoreService,
    private cdr: ChangeDetectorRef
  ) { }

  // 修正 2: 加入安全檢查
  @Input() set storeData(value: Store | null) {
    if (value && this.storeForm) { // 多判斷 this.storeForm 是否存在
      this.storeForm.patchValue(value);
    }
  }

  ngOnInit(): void {
    this.storeForm = this.fb.group({
      // 店名：加入數字允許
      storeName: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(10), Validators.pattern(this.PATTERN_STRING_WITH_NUM)]],

      // 負責人：維持純文字
      owner: ['', [Validators.required, Validators.pattern(/^[\u4e00-\u9fa5a-zA-Z]+$/)]],

      // 電話：必填
      tel: ['', [Validators.required, Validators.pattern(this.PATTERN_TEL)]],

      // 評價
      remarks: ['', [Validators.required]], // 下拉選單通常不需要 Regex，只要 Required

      // 修正 3: 手機、傳真、地址改為「非必填」(根據截圖)，並修正 Regex
      // 如果確定是必填，請把 Validators.required 加回去
      fax: ['', [Validators.pattern(this.PATTERN_TEL)]], // 改用 TEL Pattern (允許0開頭)
      mobile: ['', [Validators.pattern(this.PATTERN_TEL)]], // 改用 TEL Pattern

      // 地址：通常很難用 Regex 規範完美，建議只做長度或 Required 檢查，或允許寬鬆的格式
      address: ['', [Validators.maxLength(100)]],

      updateDate: [{ value: '2022-05-16', disabled: true }]
    });
  }

  onAdd(): void {
    this.storeForm.markAllAsTouched();
    if (this.storeForm.invalid) {
      this.showErrorToast = true;
      // 建議：既然有 setTimeout 關閉，這裡可以不變，但建議在再次點擊時重置 timer (非必要)
      setTimeout(() => {
        this.showErrorToast = false;
      }, 3000);
      return;
    }
    const rawData = this.storeForm.getRawValue();
    const store: Store = { ...rawData };
    this.storeService.addStore(store).subscribe({
      next: (res) => {
        this.showSuccessToast = true;
        this.submited = true;
        this.showErrorToast = false; // 確保錯誤關閉
        this.cdr.detectChanges();
        this.addStore.emit(res); // 建議在這裡 Emit
        setTimeout(() => {
          this.showSuccessToast = false;
        }, 3000);
      },

      // 成功後 3 秒自動關閉成功訊息
      // 修正 4: 加入錯誤處理
      error: (err) => {
        console.error('新增失敗:', err);
        this.showErrorToast = true; // 顯示錯誤訊息
      }
    });
  }

  onClear(): void {
    // 修正 5: 欄位名稱要跟 FormGroup 一模一樣
    this.storeForm.reset({
      storeName: '',
      owner: '',
      tel: '',
      fax: '',
      mobile: '',
      address: '',
      // evalution: '', // 刪除：這是錯字且不在 FormGroup
      remarks: '',     // 對應 FormGroup
      updateDate: '2022-05-16' // 對應 FormGroup (保持預設值)
    });
    this.submited = false;
  }

  onBack(): void {
    console.log('返回上一頁');
  }

  // 實作移除評價
  removeRemarks(): void {
    this.storeForm.get('remarks')?.setValue(''); // 或 null
  }

  closeToast(): void {
    this.showErrorToast = false;
    this.showSuccessToast = false; // 建議兩個都關
  }
}
