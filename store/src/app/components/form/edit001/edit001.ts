import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // 用於路由跳轉和接參數
import { StoreService } from '../../../services/storeService';
import { Store } from '../../../interface/store';

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
    // 2. 取得路由參數 (例如: /store/update/123)
    // 這裡假設你有點擊列表的「修改」按鈕，並傳遞了 ID
    // this.storeId = Number(this.route.snapshot.paramMap.get('id'));

    // if (this.storeId) {
    //   this.loadStoreData(this.storeId);
    // }
  }

  // 載入資料
  // loadStoreData(id: number): void {
  //   // 這裡你需要一個 findById 的 API
  //   // 暫時用 query 模擬，實際應該呼叫 findById
  //   const req = { storeName: '', page: { pageNumber: 0, pageSize: 10 } };

  //   // *建議*: 後端補一支 findById API 最標準
  //   // 這裡假設你有做 findByStoreId，如下：
  //   this.storeService.findByStoreId({ storeId: id } as any).subscribe({
  //     next: (res) => {
  //       if (res && res.tranrs && res.tranrs.content.length > 0) {
  //         const data = res.tranrs.content[0];
  //         // 將資料填入表單 (patchValue)
  //         this.mainForm.patchValue({
  //           storeId: data.storeId,
  //           storeName: data.storeName,
  //           owner: data.owner,
  //           tel: data.tel,
  //           mobile: data.mobile,
  //           address: data.address,
  //           evaluation: data.evaluation || '', // 避免 null
  //           updateDate: data.updateDate || new Date().toISOString().split('T')[0] // 若無日期帶今日
  //         });
  //       }
  //     },
  //     error: (err) => console.error('載入失敗', err)
  //   });
  // }

  // 修改按鈕動作
  // onUpdate(): void {
  //   // 1. 驗證表單
  //   if (this.mainForm.invalid) {
  //     this.mainForm.markAllAsTouched(); // 觸發紅字檢查
  //     this.errorMessage = '請確認必填資料!';
  //     this.showErrorToast = true;
  //     setTimeout(() => { this.showErrorToast = false; this.cdr.detectChanges(); }, 3000);
  //     return;
  //   }

  //   // 2. 準備 DTO
  //   // getRawValue() 可以拿到包含 disabled (updateDate) 的值
  //   const formValue = this.mainForm.getRawValue();

  //   // 組裝後端需要的 Request
  //   // 假設你的 Update API 需要 T001Tranrq
  //   const updateReq: Store = {
  //     ...formValue
  //   };

  //   // 3. 呼叫 Service
  //   this.storeService.updateStore(updateReq).subscribe({
  //     next: (res) => {
  //       console.log('修改成功', res);
  //       this.showSuccessToast = true;
  //       this.showErrorToast = false;
  //       this.cdr.detectChanges();

  //       // 3秒後自動跳回列表頁 (可選)
  //       setTimeout(() => {
  //         this.showSuccessToast = false;
  //         // this.router.navigate(['/store/list']);
  //       }, 3000);
  //     },
  //     error: (err) => {
  //       console.error('修改失敗', err);
  //       this.errorMessage = '修改失敗 : 更新失敗'; // 模擬截圖錯誤訊息
  //       this.showErrorToast = true;
  //       this.cdr.detectChanges();
  //       setTimeout(() => { this.showErrorToast = false; this.cdr.detectChanges(); }, 3000);
  //     }
  //   });
  // }

  // 清除按鈕 (重置為原始資料，或清空)
  // onClear(): void {
  //   if (this.storeId) {
  //     this.loadStoreData(this.storeId); // 重置回資料庫的狀態
  //   } else {
  //     this.mainForm.reset();
  //   }
  // }

  // 回上一頁
  onBack(): void {
    this.router.navigate(['/store/list']); // 請修改為你的列表頁路徑
  }
}
