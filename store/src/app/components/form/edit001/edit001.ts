
// 匯入 Angular 核心模組
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // 用於讀取路由參數和頁面跳轉
import { StoreService } from '../../../services/storeService';
import { EditTranrq } from '../../../interface/T001Tranrq';

@Component({
  selector: 'app-edit001',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit001.html',
  styleUrls: ['./edit001.css']
})
export class Edit001 implements OnInit {

  mainForm: FormGroup; // 表單物件
  storeId: number | null = null; // 儲存從路由傳過來的店家 ID

  // 提示訊息 (Toast) 的顯示控制
  showSuccessToast = false;
  showErrorToast = false;
  errorMessage = '請確認必填資料!';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute, // 用來取得當前路由資訊
    private storeService: StoreService,
    private cdr: ChangeDetectorRef
  ) {
    // 在建構函式中初始化表單
    this.mainForm = this.fb.group({
      storeId: [null], // 隱藏欄位，用來存放店家 ID
      storeName: ['', Validators.required], // 店家名稱，必填
      owner: ['', Validators.required],     // 負責人，必填
      tel: [''],
      mobile: [''],
      address: [''],
      evaluation: [''],
      updateDate: [{ value: '', disabled: true }] // 異動日期，不可編輯
    });
  }

  ngOnInit(): void {
    // 在元件初始化時，從路由的參數中取得 storeId
    const idFromUrl = this.route.snapshot.paramMap.get('storeId');

    if (idFromUrl) {
      this.storeId = Number(idFromUrl);
      // 根據取得的 ID，載入店家的詳細資料
      this.loadStoreDetail(this.storeId);
    }
  }

  // 點擊「修改」按鈕
  onUpdate(): void {
    // 步驟 1: 檢查表單是否有效
    if (this.mainForm.invalid) {
      this.mainForm.markAllAsTouched(); // 標記所有欄位為已碰觸，以顯示錯誤訊息
      this.errorMessage = '請確認必填資料!';
      this.showErrorToast = true;
      return;
    }

    // 步驟 2: 組合要送到後端的 Request 資料
    const formValue = this.mainForm.getRawValue(); // 使用 getRawValue() 才能取得被禁用的欄位值
    const tranrq: EditTranrq = {
      storeId: this.storeId ?? 0, // 使用目前儲存的 storeId
      storeName: formValue.storeName,
      owner: formValue.owner,
      tel: formValue.tel,
      mobile: formValue.mobile,
      address: formValue.address,
      evaluation: formValue.evaluation,
      fax: formValue.fax || '',
      remarks: formValue.remarks || ''
    };

    // 步驟 3: 呼叫 API 服務
    this.storeService.updateStore(tranrq).subscribe({
      next: (res) => {
        // 更新成功
        this.showSuccessToast = true;
        this.showErrorToast = false;
        this.cdr.detectChanges(); // 手動觸發畫面更新
      },
      error: (err) => {
        // 更新失敗
        console.error('修改失敗', err);
        this.errorMessage = '修改失敗 : 更新失敗';
        this.showErrorToast = true;
        this.cdr.detectChanges();
      }
    });
  }

  /**
   * 根據 ID 載入店家資料並填入表單
   * @param id 店家 ID
   */
  loadStoreDetail(id: number): void {
    const requestPayload = {
      MWHEADER: { MSGID: 'XXA-C-STOREQ001' },
      TRANRQ: {
        storeId: id,
        storeName: '',
        page: { pageNumber: 0, pageSize: 1 } // 查單筆
      }
    };

    // 呼叫 API 取得資料
    this.storeService.findByStoreId(requestPayload).subscribe({
      next: (res) => {
        // 檢查回傳資料是否成功
        if (res && res.TRANRS && res.TRANRS.items && res.TRANRS.items.length > 0) {
          const data = res.TRANRS.items[0]; // 取回傳列表的第一筆資料
          // 使用 patchValue 將資料填入表單
          this.mainForm.patchValue({
            store_id: data.storeId,
            storeName: data.storeName,
            owner: data.owner,
            tel: data.tel,
            mobile: data.mobile,
            address: data.address,
            evaluation: data.evaluation,
            updateDate: data.updateTime
          });
        }
      },
      error: (err) => console.error('API 連線失敗', err)
    });
  }

  // 點擊「清除」按鈕，重設表單
  onClear(): void {
    if (this.storeId) {
      // 如果有 storeId，代表是編輯模式，應還原成原始資料
      this.loadStoreDetail(this.storeId);
    } else {
      // 如果沒有 storeId，則清空表單
      this.mainForm.reset();
    }
  }

  // 點擊「回上一頁」按鈕
  onBack(): void {
    this.router.navigate(['/store/list']); // 跳轉回路徑為 /store/list 的頁面
  }
}
