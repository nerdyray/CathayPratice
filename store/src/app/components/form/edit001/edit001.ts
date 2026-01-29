import { Tranrq } from './../../../interface/createTranrq';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // 用於路由跳轉和接參數
import { StoreService } from '../../../services/storeService';
import { T001Tranrq } from '../../../interface/T001Tranrq';

@Component({
  selector: 'app-edit001',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit001.html',
  styleUrls: ['./edit001.css']
})
export class Edit001 implements OnInit {

  mainForm: FormGroup;
  storeId: number | null = null; // 儲存目前的 StoreID

  // Toast 控制
  showSuccessToast = false;
  showErrorToast = false;
  errorMessage = '請確認必填資料!';

  constructor(

    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private storeService: StoreService,
    private cdr: ChangeDetectorRef
  ) {
    // 1. 初始化表單
    this.mainForm = this.fb.group({
      storeId: [null], // 隱藏欄位
      storeName: ['', Validators.required],
      owner: ['', Validators.required],
      tel: [''],
      mobile: [''],
      address: [''],
      evaluation: [''],
      updateDate: [{ value: '', disabled: true }] // 設為 disabled，使用者不能改
    });
  }

  ngOnInit(): void {
    const idFromUrl = this.route.snapshot.paramMap.get('storeId');

    if (idFromUrl) {
      this.storeId = Number(idFromUrl);
      console.log('✅ 成功鎖定 ID:', this.storeId);

      // 🔥【關鍵】這行一定要加！不然 loadStoreDetail 永遠不會執行！
      this.loadStoreDetail(this.storeId);

    }
  }

  // 修改按鈕動作
  onUpdate(): void {
    // 1. 驗證表單
    if (this.mainForm.invalid) {
      this.mainForm.markAllAsTouched(); // 觸發紅字檢查
      this.errorMessage = '請確認必填資料!';
      this.showErrorToast = true;
      setTimeout(() => { this.showErrorToast = false; this.cdr.detectChanges(); }, 3000);
      return;
    }

    // 2. 準備 DTO
    // getRawValue() 可以拿到包含 disabled (updateDate) 的值
    const formValue = this.mainForm.getRawValue();
    const rawData = {
      storeId: this.storeId!, // 確保變數名稱是 storeId (駝峰)
      storeName: formValue.storeName,
      owner: formValue.owner,
      tel: formValue.tel,
      mobile: formValue.mobile,
      address: formValue.address,
      evaluation: formValue.evaluation,
      fax: formValue.fax || '',
      remarks: formValue.remarks || ''
      // date: ... (如果有需要)
    };
    // 3. 呼叫 Service
    this.storeService.updateStore(rawData as any).subscribe({
      next: (res) => {
        console.log('修改成功', res);
        this.showSuccessToast = true;
        this.showErrorToast = false;
        this.cdr.detectChanges();
        setTimeout(() => {
          this.showSuccessToast = false;

        }, 3000);
      },
      error: (err) => {
        console.error('修改失敗', err);
        this.errorMessage = '修改失敗 : 更新失敗'; // 模擬截圖錯誤訊息
        this.showErrorToast = true;
        this.cdr.detectChanges();
        setTimeout(() => { this.showErrorToast = false; this.cdr.detectChanges(); }, 3000);
      }
    });
  }

  loadStoreDetail(id: number): void {
    console.log('準備向後端查詢 ID:', id);
    const requestPayload = {
      MWHEADER: {
        MSGID: 'XXA-C-STOREQ001' // 查詢類的 MSGID
      },
      TRANRQ: {
        storeId: id,
        storeName: '',
        page: {
          pageNumber: 0,
          pageSize: 1 // 查單筆，給 1 即可
        }
      }
    };

    // 2. 發送請求
    this.storeService.findByStoreId(requestPayload).subscribe({
      next: (res) => {
        // 3. 解析回傳資料 (Response)
        // 後端回傳的是 StoreResponse<Q001Tranrs>，裡面通常是 List (Page)
        if (res && res.TRANRS && res.TRANRS.items && res.TRANRS.items.length > 0) {
          // 因為是用 ID 查，理論上只會有一筆，抓第 0 筆
          const data = res.TRANRS.items[0];
          // 4. 填表
          this.mainForm.patchValue({
            store_id: data.storeId,
            storeName: data.storeName,
            owner: data.owner,
            tel: data.tel,
            mobile: data.mobile,
            address: data.address,
            evaluation: data.evaluation,
            updateDate: data.updateTime // 視情況處理日期格式
          });
        }
      },
      error: (err) => console.error('API 連線失敗', err)
    });
  }

  // 清除按鈕 (重置為原始資料，或清空)
  onClear(): void {
    this.mainForm.reset();
  }

  // 回上一頁
  onBack(): void {
    this.router.navigate(['/store/list']); // 請修改為你的列表頁路徑
  }

}
